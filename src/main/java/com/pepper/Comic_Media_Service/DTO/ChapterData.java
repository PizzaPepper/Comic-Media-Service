package com.pepper.Comic_Media_Service.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterData {
    private UUID id;
    private String title;
    private BigDecimal number;
}
