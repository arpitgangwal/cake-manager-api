package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dto.CakeRequestDto;
import com.waracle.cakemgr.dto.CakeResponseDto;

import java.util.List;

public interface CakeService {
    CakeResponseDto addCake(CakeRequestDto cakeRequestDto);
    List<CakeResponseDto> getAllCakes();
}
