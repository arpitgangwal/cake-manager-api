package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.dto.CakeRequestDto;
import com.waracle.cakemgr.dto.CakeResponseDto;
import com.waracle.cakemgr.entity.CakeEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("VanillaCakeService")
@Slf4j
public class CakeServiceImpl implements CakeService {
    @Autowired
    CakeDao cakeDao;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CakeResponseDto addCake(CakeRequestDto cakeRequestDto) {
        CakeResponseDto cakeResponseDto = modelMapper.map(cakeDao.addCake(modelMapper.map(cakeRequestDto,CakeEntity.class)), CakeResponseDto.class);
       // log.debug("cake added successfully", cakeResponseDto);
        return cakeResponseDto;
    }

    @Override
    public List<CakeResponseDto> getAllCakes() {
        List<CakeResponseDto> cakeResponseDtoList = modelMapper.map(cakeDao.getCakeList(),List.class);
       // log.debug("Cakes list successfully fetched: ",cakeResponseDtoList);
        return cakeResponseDtoList;
    }
}
