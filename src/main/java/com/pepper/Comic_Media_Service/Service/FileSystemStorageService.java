package com.pepper.Comic_Media_Service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pepper.Comic_Media_Service.Configuration.StorageProperties;
import com.pepper.Comic_Media_Service.DTO.MultiPartPageData;
import com.pepper.Comic_Media_Service.DTO.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileSystemStorageService implements StorageService {

    private final StorageProperties properties;

    @Override
    public List<PageData> storeChapter(List<MultiPartPageData> pages) {

        UUID newChapterVID = UUID.randomUUID();

        List<PageData> storedPages = new ArrayList<>();

        Path rootLocation = Path.of(properties.getLocation()); 

        Path chapterFolder = rootLocation.resolve("chapter-versions").resolve(newChapterVID.toString());

        for (MultiPartPageData page : pages) {
            MultipartFile file = page.getFile();
            
            if (file == null || file.isEmpty()) {
                continue; 
            }

            String originalName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalName); 
            
            String dottedExtension = (extension != null && !extension.isEmpty()) ? "." + extension : ".jpg";

            String fileName = String.format("%d%s", page.getOrderNumber(), dottedExtension);

            Path destinationPath = chapterFolder.resolve(fileName).normalize().toAbsolutePath();
            
            if (!destinationPath.startsWith(chapterFolder.toAbsolutePath())) {
                throw new RuntimeException("Attempt to access a directory outside the permitted scope.");
            }

            try {
                java.io.File targetFile = destinationPath.toFile();

                FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

                String relativeUrl = String.format("/uploads/chapter-versions/%s/%s", newChapterVID, fileName);

                storedPages.add(PageData.builder()
                        .pageNumber(page.getOrderNumber())
                        .imagePath(relativeUrl)
                        .build());

            } catch (IOException e) {
                throw new RuntimeException("Error saving page " + page.getOrderNumber() + " of the chapter", e);
            }
        }

        return storedPages;
    }
    
}
