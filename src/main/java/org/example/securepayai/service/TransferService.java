package org.example.securepayai.service;

import lombok.RequiredArgsConstructor;
import org.example.securepayai.dto.CreateTransferRequest;
import org.example.securepayai.dto.CreatedTransferResponse;
import org.example.securepayai.entity.Transfer;
import org.example.securepayai.entity.TransferStatus;
import org.example.securepayai.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
//the RequiredArgsConstructor is for constructor injection. better than @Autowired
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

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

        return CreatedTransferResponse.builder()
                .transferId(saveTransfer.getId())
                .status(saveTransfer.getStatus().name())
                .message("Transfer request created successfully")
                .build();
    }
}
