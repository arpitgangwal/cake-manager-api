package com.waracle.cakemgr.controller;


import com.waracle.cakemgr.dto.CakeRequestDto;
import com.waracle.cakemgr.dto.CakeResponseDto;
import com.waracle.cakemgr.service.CakeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;

@RestController
@Slf4j
@SecurityRequirement(name="Authentication")
public class CakeManagerController {
    private CakeService cakeService;
    public CakeManagerController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @PostMapping(path = "/cakes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CakeResponseDto> createCreditCard(@Validated @RequestBody CakeRequestDto cakeRequestDto) throws ParseException {
        log.info("Got cake added request");
        return ResponseEntity.status(HttpStatus.CREATED).body(cakeService.addCake(cakeRequestDto));
    }

    @GetMapping(path = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CakeResponseDto>>getCreditCardList() {
         log.info("Request received for listing all cakes");
        return ResponseEntity.status(HttpStatus.OK).body(cakeService.getAllCakes());

    }
    @GetMapping( path = "/",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getCreditCardListFile() {
        log.info("Request received for downloading all cakes");
        return ResponseEntity.status(HttpStatus.OK).body(cakeService.getAllCakes().toString().getBytes(StandardCharsets.UTF_8));

    }
}
