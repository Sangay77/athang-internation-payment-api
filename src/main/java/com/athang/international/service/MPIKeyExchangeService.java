package com.athang.international.service;


import com.athang.international.dto.MPIKeyReq;
import com.athang.international.dto.MPIKeyRes;
import com.athang.international.utils.RSAKeyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class MPIKeyExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(MPIKeyExchangeService.class);

    @Autowired
    private RSAKeyProvider rsaKeyProvider;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${mpi.keyexchange.url}")
    private String keyExchangeUrl;

    @Value("${mpi.merchant.usd.id}")
    private String merchantId;

    public MPIKeyRes initiateKeyExchange() throws Exception {

        String purchaseId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12);

        MPIKeyReq request = new MPIKeyReq();
        request.setMerchantId(merchantId);
        request.setPurchaseId(purchaseId);
        request.setPubKey(rsaKeyProvider.getPublicKeyBase64Url());

        logger.info("Initiating MPI Key Exchange | merchantId={} | purchaseId={}",
                merchantId, purchaseId);

        WebClient webClient = webClientBuilder.build();
        long startTime = System.currentTimeMillis();


        MPIKeyRes acsResponse = webClient
                .post()
                .uri(keyExchangeUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(MPIKeyRes.class)
                .block();
        long durationMs = System.currentTimeMillis() - startTime;
        logger.info("+++Response received in {} ms", durationMs);
        logger.info("+++ Raw Response: {}", acsResponse);

        return acsResponse;
    }
}
