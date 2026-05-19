package com.pepper.Comic_Media_Service.DTO.Request;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadChapterRequest {
    private List<MultipartFile> pages;
    private BigDecimal number;
    private String title;
}
