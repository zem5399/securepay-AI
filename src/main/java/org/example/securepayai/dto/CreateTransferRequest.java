package org.example.securepayai.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransferRequest {
    private String senderName;
    private String receiverName;
    private String receiverCountry;
    private BigDecimal amount;
    private String currency;
    private String purpose;
}
