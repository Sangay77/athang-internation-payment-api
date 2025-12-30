package com.athang.international.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MPIKeyReq {

    private String merchantId;   // AN(15), mandatory
    private String purchaseId;   // AN(20), mandatory
    private String pubKey;     // AN(392), mandatory
    private String macANS;       // AN(344), optional
}
