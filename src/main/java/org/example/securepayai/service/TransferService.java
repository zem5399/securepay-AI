package org.example.securepayai.service;

import lombok.RequiredArgsConstructor;
import org.example.securepayai.dto.*;
import org.example.securepayai.entity.Transfer;
import org.example.securepayai.entity.TransferStatus;
import org.example.securepayai.exception.TransferNotFoundException;
import org.example.securepayai.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//the RequiredArgsConstructor is for constructor injection. better than @Autowired
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferProcessorService transferProcessorService;

    public CreatedTransferResponse creatTransfer(CreateTransferRequest request) {

        Transfer transfer = Transfer.builder()
                .senderName(request.getSenderName())
                .receiverName(request.getReceiverName())
                .receiverCountry(request.getReceiverCountry())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .purpose(request.getPurpose())
                .status(TransferStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        Transfer saveTransfer = transferRepository.save(transfer);
        transferProcessorService.processTransferAsync(saveTransfer.getId());

        return CreatedTransferResponse.builder()
                .transferId(saveTransfer.getId())
                .status(saveTransfer.getStatus().name())
                .message("Transfer request created successfully")
                .build();
    }

    public List<TransferResponse> getAllTransfer() {
        return transferRepository.findAll()
                .stream()
                .map(transfer -> this.mapToTransferResponse(transfer))
                .toList();
    }

    public TransferResponse getTransferById(Long id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        return mapToTransferResponse(transfer);
    }

    public TransferResponse deleteById(Long id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        transferRepository.delete(transfer);

        return mapToTransferResponse(transfer);
    }

    public TransferResponse updateTransfer(Long id, UpdateTransferRequest request) {

        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        transfer.setSenderName(request.getSenderName());
        transfer.setReceiverName(request.getReceiverName());
        transfer.setReceiverCountry(request.getReceiverCountry());
        transfer.setAmount(request.getAmount());
        transfer.setCurrency(request.getCurrency());
        transfer.setPurpose(request.getPurpose());

        Transfer updateTransfer = transferRepository.save(transfer);

        return mapToTransferResponse(updateTransfer);
    }

    public TransferResponse updateTransferStatus(Long id, UpdateTransferStatusRequest request) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        transfer.setStatus(request.getStatus());
        Transfer updateTransfer = transferRepository.save(transfer);

        return mapToTransferResponse(updateTransfer);
    }

    private TransferResponse mapToTransferResponse(Transfer transfer) {
        return TransferResponse.builder()
                .id(transfer.getId())
                .senderName(transfer.getSenderName())
                .receiverName(transfer.getReceiverName())
                .receiverCountry(transfer.getReceiverCountry())
                .amount(transfer.getAmount())
                .currency(transfer.getCurrency())
                .purpose(transfer.getPurpose())
                .status(transfer.getStatus())
                .createdAt(transfer.getCreatedAt())
                .build();
    }
}
