package com.athang.international.dto;

import lombok.Data;

@Data
public class MPIKeyRes {

    private String merchantId;
    private String purchaseId;
    private String pubKey;
    private String errorCode;
}
