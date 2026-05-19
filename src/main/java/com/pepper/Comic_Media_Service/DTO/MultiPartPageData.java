package com.pepper.Comic_Media_Service.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MultiPartPageData {
    private MultipartFile file;
    private Integer orderNumber;
}
