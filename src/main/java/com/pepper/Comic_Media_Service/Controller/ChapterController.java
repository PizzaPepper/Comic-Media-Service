package com.pepper.Comic_Media_Service.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pepper.Comic_Media_Service.DTO.ChapterData;
import com.pepper.Comic_Media_Service.DTO.ChapterVersionData;
import com.pepper.Comic_Media_Service.DTO.PageData;
import com.pepper.Comic_Media_Service.DTO.Request.CreateChapterDataRequest;
import com.pepper.Comic_Media_Service.DTO.Request.CreateChapterRequest;
import com.pepper.Comic_Media_Service.DTO.Response.GenericResponse;
import com.pepper.Comic_Media_Service.Exception.ResourceAlreadyExistsException;
import com.pepper.Comic_Media_Service.Exception.ResourceNotFoundException;
import com.pepper.Comic_Media_Service.Service.ChapterService;
import com.pepper.Comic_Media_Service.Service.StorageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comic/{comicID}/chapters")
public class ChapterController {

        private final ChapterService chapterService;
        private final StorageService storageService;
        

        @GetMapping()
        public ResponseEntity<List<ChapterData>> getAllChapters(@PathVariable("comicID") UUID comicID)
                        throws ResourceNotFoundException {

                List<ChapterData> chapters = chapterService.byComicId(comicID);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(chapters);
        }

        @PostMapping(value = "/{chapterID}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        public ResponseEntity<GenericResponse> uploadChapter(
                @PathVariable("chapterID") UUID chapterID,
                @RequestHeader("X-Scan-Group-Id") UUID groupID,
                @RequestParam("pages") List<MultipartFile> pages
        ) throws ResourceNotFoundException, ResourceAlreadyExistsException {
                
                
                List<PageData> pagesData = storageService.storeChapter(chapterService.buildMultiPartPageData(pages));

                ChapterVersionData pagesAdded = chapterService.createChapterVersionData(chapterID, groupID, pagesData);
                
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(GenericResponse.builder()
                                .success(true)
                                .message(String.format("It was uploaded %d pages for '%s' - '%s'", 
                                        pagesAdded.getPages().size(), 
                                        pagesAdded.getChapter().getTitle(),
                                        pagesAdded.getChapter().getComic().getTitle()))
                                .timestamp(LocalDateTime.now())
                        .build());
        }

        @PostMapping()
        public ResponseEntity<GenericResponse> createChapter(
                        @PathVariable("comicID") UUID comicID,
                        @Valid @RequestBody CreateChapterRequest request
        ) throws ResourceNotFoundException, ResourceAlreadyExistsException {
                
                chapterService.createChapterData(CreateChapterDataRequest.builder()
                                .comicId(comicID)
                                .title(request.getTitle())
                                .newNumber(request.getNumber())
                                .build());

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(GenericResponse.builder()
                                                .success(true)
                                                .message("\'" + request.getTitle()
                                                                + "\' chapter was created successfully")
                                                .timestamp(LocalDateTime.now()).build());

        }

}
