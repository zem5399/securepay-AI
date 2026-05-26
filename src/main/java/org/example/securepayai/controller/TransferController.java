package org.example.securepayai.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.securepayai.dto.*;
import org.example.securepayai.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;

    @PostMapping
    public CreatedTransferResponse createTransfer(@Valid @RequestBody CreateTransferRequest request) {
        return service.creatTransfer(request);
    }

    @GetMapping
    public List<TransferResponse> getAllTransfers() {
        return service.getAllTransfer();
    }

    @GetMapping("/{id}")
    public TransferResponse getTransferById(@PathVariable Long id) {
        return service.getTransferById(id);
    }

    @DeleteMapping("/{id}")
    public TransferResponse deleteTransferById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public TransferResponse updateTransfer(@PathVariable Long id, @Valid @RequestBody UpdateTransferRequest request) {
        return service.updateTransfer(id, request);
    }

    @PatchMapping("/{id}/status")
    public TransferResponse updateTransferStatus(@PathVariable Long id, @RequestBody UpdateTransferStatusRequest request) {
        return service.updateTransferStatus(id, request);
    }
}
