package org.example.securepayai.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.securepayai.dto.CreateTransferRequest;
import org.example.securepayai.dto.CreatedTransferResponse;
import org.example.securepayai.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;

    @PostMapping
    public CreatedTransferResponse createTransfer(@Valid @RequestBody CreateTransferRequest request) {
        return service.creatTransfer(request);
    }
}
