package com.pepper.Comic_Media_Service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pepper.Comic_Media_Service.DTO.ChapterData;
import com.pepper.Comic_Media_Service.DTO.ChapterVersionData;
import com.pepper.Comic_Media_Service.DTO.MultiPartPageData;
import com.pepper.Comic_Media_Service.DTO.PageData;
import com.pepper.Comic_Media_Service.DTO.Request.CreateChapterDataRequest;
import com.pepper.Comic_Media_Service.Entity.ChapterEntity;
import com.pepper.Comic_Media_Service.Entity.ChapterVersionEntity;
import com.pepper.Comic_Media_Service.Entity.ComicEntity;
import com.pepper.Comic_Media_Service.Entity.PageEntity;
import com.pepper.Comic_Media_Service.Entity.ScanGroupEntity;
import com.pepper.Comic_Media_Service.Exception.ResourceAlreadyExistsException;
import com.pepper.Comic_Media_Service.Exception.ResourceNotFoundException;
import com.pepper.Comic_Media_Service.Mapper.ChapterMapper;
import com.pepper.Comic_Media_Service.Repository.ChapterRepository;
import com.pepper.Comic_Media_Service.Repository.ChapterVersionRepository;
import com.pepper.Comic_Media_Service.Repository.ComicRepository;
import com.pepper.Comic_Media_Service.Repository.ScanGroupRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChapterService {

        private final ComicRepository comicRepository;
        private final ChapterRepository chapterRepository;
        private final ChapterVersionRepository chapterVersionRepository;
        private final ScanGroupRepository scanGroupRepository;

        private final ChapterMapper chapterMapper;

        @Transactional
        public ChapterData createChapterData(CreateChapterDataRequest request)
                        throws ResourceAlreadyExistsException, ResourceNotFoundException {

                ComicEntity comic = comicRepository.findById(request.getComicId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "'" + request.getComicId() + "' Id Comic doesn't exist"));

                ChapterEntity cItExists = chapterRepository.findByComicAndNumber(comic, request.getNewNumber())
                                .orElse(null);

                if (cItExists != null) {
                        throw new ResourceAlreadyExistsException("Chapter \'" + request.getNewNumber() + "\' of the \'"
                                        + comic.getTitle() + "\' already exists");
                }

                ChapterEntity newChapter = ChapterEntity.builder()
                                .comic(comic)
                                .title(request.getTitle())
                                .number(request.getNewNumber())
                                .createdAt(LocalDateTime.now())
                                .build();

                return chapterMapper.toData(chapterRepository.save(newChapter)) ;
        }

        @Transactional
        public ChapterVersionData createChapterVersionData(UUID chapterID, UUID groupID, List<PageData> pagesData)
                        throws ResourceNotFoundException, ResourceAlreadyExistsException {

                ScanGroupEntity group = scanGroupRepository.findById(groupID)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                String.format("Group 's%' not found", groupID.toString())));

                ChapterEntity chapter = chapterRepository.findById(chapterID)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                String.format("Chapter '%s' not found", chapterID.toString())));

                ChapterVersionData itExists = chapterVersionRepository.findByChapter(chapter).orElse(null);

                if (itExists != null) {
                        throw new ResourceAlreadyExistsException(
                                        String.format("Chapter number '%d' from '%s' already exists",
                                                        chapter.getNumber(), chapter.getComic().getTitle()));
                }
                
                ChapterVersionEntity chapterVersion = ChapterVersionEntity.builder()
                                .chapter(chapter)
                                .scanGroup(group)
                                .build();


                List<PageEntity> pageEntities = pagesData
                                .stream()
                                .map(pageData -> PageEntity.builder()
                                                .chapterVersion(chapterVersion)
                                                .pageNumber(pageData.getPageNumber())
                                                .imagePath(pageData.getImagePath())
                                                .build())
                                .toList();

                chapterVersion.setPages(pageEntities);

                ChapterVersionEntity savedVersion = chapterVersionRepository.save(chapterVersion);


                return chapterMapper.toData(savedVersion);

        }

        public List<MultiPartPageData> buildMultiPartPageData(List<MultipartFile> pages) {
                return pages.stream()
                                .map(page -> {
                                        int order = Integer.parseInt(
                                                        FilenameUtils.removeExtension(page.getOriginalFilename()));

                                        return MultiPartPageData.builder()
                                                        .file(page)
                                                        .orderNumber(order)
                                                        .build();

                                }).toList();

        }
}
