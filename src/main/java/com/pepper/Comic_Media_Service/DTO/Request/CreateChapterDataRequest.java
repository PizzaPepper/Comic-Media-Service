package com.pepper.Comic_Media_Service.DTO.Request;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateChapterDataRequest {
    private UUID comicId;
    private BigDecimal newNumber;
    private String title;
}
