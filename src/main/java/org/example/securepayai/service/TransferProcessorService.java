package org.example.securepayai.service;


import lombok.RequiredArgsConstructor;
import org.example.securepayai.entity.Transfer;
import org.example.securepayai.entity.TransferStatus;
import org.example.securepayai.exception.TransferNotFoundException;
import org.example.securepayai.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class TransferProcessorService {
    private final ExecutorService transferExecutorService;
    private final TransferRepository transferRepository;

    public void processTransferAsync(Long transferId) {
        transferExecutorService.submit(() -> processTransfer(transferId));
    }
    private void processTransfer(Long transferId) {
        try {
            Transfer transfer = transferRepository.findById(transferId)
                    .orElseThrow(() -> new TransferNotFoundException(transferId));

            transfer.setStatus(TransferStatus.PROCESSING);
            transferRepository.save(transfer);

            Thread.sleep(3000);

            boolean success = new Random().nextBoolean();

            transfer.setStatus(success ? TransferStatus.COMPLETED : TransferStatus.FAILED);
            transferRepository.save(transfer);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
