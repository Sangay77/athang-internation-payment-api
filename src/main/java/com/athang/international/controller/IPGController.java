package com.athang.international.controller;

import com.athang.international.dto.MPIKeyRes;
import com.athang.international.service.MPIKeyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPGController {

    @Autowired
    private MPIKeyExchangeService mpiKeyExchangeService;

    @PostMapping("/key-exchange")
    public MPIKeyRes initiateKeyExchange() throws Exception {
        return mpiKeyExchangeService.initiateKeyExchange();
    }
}
