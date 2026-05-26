package org.example.securepayai.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse {

    private LocalDateTime timeStamp;

    private int status;

    private String message;

    private Map<String, String> error;
}
