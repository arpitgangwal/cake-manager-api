package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.CakeRequestDto;
import com.waracle.cakemgr.dto.CakeResponseDto;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CakeManagerControllerTest {

    @Mock
    private CakeService cakeService;

    @InjectMocks
    private CakeManagerController cakeController;
    private CakeResponseDto cakeResponseDto;
    @BeforeEach
    void setUp(){
        cakeResponseDto = new CakeResponseDto();
        cakeResponseDto.setDescription("desc");
        cakeResponseDto.setImageUrl("http://cakeurl");
        cakeResponseDto.setName("cake1");
        List<CakeResponseDto> cakeResponseDtoList=Arrays.asList(cakeResponseDto);
        when(cakeService.getAllCakes()).thenReturn(cakeResponseDtoList);
    }

    @Test
    void createCreditCard()  {
        CakeRequestDto cakeRequestDto = new CakeRequestDto();
        cakeRequestDto.setDescription("desc");
        cakeRequestDto.setImageUrl("http://cakeurl");
        cakeRequestDto.setName("cake1");
        when(cakeService.addCake(cakeRequestDto)).thenReturn(cakeResponseDto);
        ResponseEntity<CakeResponseDto> response = cakeController.createCreditCard(cakeRequestDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(cakeResponseDto);
    }

    @Test
    void getCreditCardList() {

        ResponseEntity<List<CakeResponseDto>> response = cakeController.getCreditCardList();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().get(0)).isEqualTo(cakeResponseDto);
    }

    @Test
    void getCreditCardListFile() {
        ResponseEntity<byte[]> response = cakeController.getCreditCardListFile();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
    }
}