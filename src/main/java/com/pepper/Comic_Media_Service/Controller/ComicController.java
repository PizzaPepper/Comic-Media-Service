package com.pepper.Comic_Media_Service.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepper.Comic_Media_Service.DTO.ComicData;
import com.pepper.Comic_Media_Service.DTO.Request.CreateComicDataRequest;
import com.pepper.Comic_Media_Service.DTO.Response.GenericResponse;
import com.pepper.Comic_Media_Service.Exception.BadRequestException;
import com.pepper.Comic_Media_Service.Exception.ResourceAlreadyExistsException;
import com.pepper.Comic_Media_Service.Exception.ResourceNotFoundException;
import com.pepper.Comic_Media_Service.Service.ComicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comic")
public class ComicController {

    private final ComicService comicService;

    @PostMapping
    public ResponseEntity<GenericResponse> createComic(
            @Valid @RequestBody CreateComicDataRequest request)
            throws ResourceAlreadyExistsException, BadRequestException {

        comicService.createComicData(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.builder()
                        .message("\'" + request.getTitle() + "\' was created successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping("/{comicID}")
    public ResponseEntity<ComicData> getChapterById(@PathVariable("comicID") UUID comicID)
            throws ResourceNotFoundException {

        ComicData response = comicService.getComicById(comicID);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
