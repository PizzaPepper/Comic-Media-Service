package com.pepper.Comic_Media_Service.DTO.Response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String body;
    private LocalDateTime timestamp;
}
