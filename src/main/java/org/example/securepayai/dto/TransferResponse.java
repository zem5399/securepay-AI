package org.example.securepayai.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.securepayai.entity.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class TransferResponse {

    private Long id;
    private String senderName;
    private String receiverName;
    private String receiverCountry;
    private BigDecimal amount;
    private String currency;
    private String purpose;
    private TransferStatus status;
    private LocalDateTime createdAt;
}
