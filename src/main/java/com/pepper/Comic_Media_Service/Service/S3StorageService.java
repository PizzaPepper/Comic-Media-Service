package com.pepper.Comic_Media_Service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.InvalidRequestException;

import com.pepper.Comic_Media_Service.DTO.MultiPartPageData;
import com.pepper.Comic_Media_Service.DTO.PageData;
import com.pepper.Comic_Media_Service.Exception.aws.ComicStorageException;
import com.pepper.Comic_Media_Service.Util.S3Utils;


@Service
public class S3StorageService implements StorageService {

    private final S3Client s3Client;
    private final String storageComicBucketName;

    public S3StorageService(
        S3Client s3Client,
        @Value("${aws.s3.comic-storage-bucket-name}") String storageComicBucketName
    ) {
        this.s3Client = s3Client;
        this.storageComicBucketName = storageComicBucketName;
    }

    // @Value("${aws.s3.comic-cover-bucket-name}")
    // private String storageComicCoverBucketName;


    @Override
    public List<PageData> storeChapter(List<MultiPartPageData> pages) throws ComicStorageException {
        
        boolean itExists = S3Utils.doesBucketExist(storageComicBucketName, s3Client);

        if(!itExists) {
            throw new ComicStorageException("The bucket '"+storageComicBucketName+"' does not exist");
        }

        String keyBase = UUID.randomUUID().toString();

        List<PageData> pageData = new ArrayList<>();

        for(MultiPartPageData page : pages) {
            if(page.getFile().isEmpty()) continue;

            try {
                String key = keyBase + "/" + page.getFile().getOriginalFilename();
                PutObjectRequest objectRequest = PutObjectRequest.builder()
                        .bucket(storageComicBucketName)
                        .key(key)
                        .contentType(page.getFile().getContentType())
                        .build();

                s3Client.putObject(objectRequest, RequestBody.fromBytes(page.getFile().getBytes()));

                pageData.add(
                    PageData.builder()
                    .imagePath(key)
                    .pageNumber(page.getOrderNumber())
                    .build()
                );
            } catch (InvalidRequestException | IOException e) {
                throw new ComicStorageException("Failed to upload file: " + page.getFile().getName() + "\n"+ e.toString());
            }
        }

        return pageData;
    }


}
