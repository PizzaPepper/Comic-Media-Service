package com.pepper.Comic_Media_Service.DTO.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseWithData {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private Object data;
}
