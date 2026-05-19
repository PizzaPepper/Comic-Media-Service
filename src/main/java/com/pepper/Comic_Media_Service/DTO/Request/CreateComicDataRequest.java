package com.pepper.Comic_Media_Service.DTO.Request;

import com.pepper.Comic_Media_Service.DTO.ComicStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateComicDataRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    
    private ComicStatus status;
}
