package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.dto.CakeRequestDto;
import com.waracle.cakemgr.dto.CakeResponseDto;
import com.waracle.cakemgr.entity.CakeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CakeServiceImplTest {
    @InjectMocks
    CakeServiceImpl cakeService;
    @Mock
    CakeDao cakeDao;
    @Mock
    private CakeResponseDto cakeResponseDto;
    @Mock
    CakeEntity cakeEntity;
    List<CakeEntity> cakeEntityList;
    @Mock
    ModelMapper modelMapper;
    List<CakeResponseDto> cakeResponseDtoList;
    @BeforeEach
    void setUp() {
        cakeResponseDto = new CakeResponseDto();
        cakeResponseDto.setDescription("desc");
        cakeResponseDto.setImageUrl("http://cakeurl");
        cakeResponseDto.setName("cake1");
        cakeResponseDtoList=Arrays.asList(cakeResponseDto);
        cakeEntity = new CakeEntity();
        cakeEntity.setDescription("desc");
        cakeEntity.setImageUrl("http://cakeurl");
        cakeEntity.setName("cake1");
        cakeEntityList = Arrays.asList(cakeEntity);

    }

    @Test
    void addCake() {
        CakeRequestDto cakeRequestDto = new CakeRequestDto();
        cakeRequestDto.setDescription("desc");
        cakeRequestDto.setImageUrl("http://cakeurl");
        cakeRequestDto.setName("cake1");
        when(cakeDao.addCake(cakeEntity)).thenReturn(cakeEntity);
        when(modelMapper.map(cakeRequestDto,CakeEntity.class)).thenReturn(cakeEntity);
        when(modelMapper.map(cakeEntity,CakeResponseDto.class)).thenReturn(cakeResponseDto);
        CakeResponseDto cakeResponseDto = cakeService.addCake(cakeRequestDto);
        assertNotNull(cakeResponseDto);
    }

    @Test
    void getAllCakes() {

        when(cakeDao.getCakeList()).thenReturn(cakeEntityList);
        when(modelMapper.map(cakeEntityList,List.class)).thenReturn(cakeResponseDtoList);
        when(modelMapper.map(cakeEntity,CakeResponseDto.class)).thenReturn(cakeResponseDto);
        List<CakeResponseDto> cakeResponseDtoList = cakeService.getAllCakes();
        assertNotNull(cakeResponseDtoList);
    }
}