package com.pepper.Comic_Media_Service.DTO;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComicData {
    private UUID id;
    private String title;
    private String author;
    private String status;
}
