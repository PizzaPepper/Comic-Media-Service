package com.pepper.Comic_Media_Service.DTO.Request;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  
@AllArgsConstructor
public class CreateChapterRequest {
    @NotBlank
    private String title;
    @NotNull
    private BigDecimal number;
}
