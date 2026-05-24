package org.example.securepayai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatedTransferResponse {
    private Long transferId;
    private String status;
    private String message;
}
