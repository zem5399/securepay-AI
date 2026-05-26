package org.example.securepayai.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.securepayai.entity.TransferStatus;

@Getter
@Setter
public class UpdateTransferStatusRequest {

    private TransferStatus status;
}
