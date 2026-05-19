package com.pepper.Comic_Media_Service.DTO;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageData {
    private UUID id;
    private Integer pageNumber;
    private String imagePath;
}
