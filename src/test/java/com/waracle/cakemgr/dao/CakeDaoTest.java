package com.waracle.cakemgr.dao;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@SpringBootTest
class CakeDaoTest {
    @InjectMocks
    private CakeDao cakeDao;
    @Mock
    private CakeRepository cakeRepository;
    CakeEntity cakeEntity;
    List<CakeEntity> cakeEntityList;
    @BeforeEach
    void setUp() {
        cakeEntity = new CakeEntity();
        cakeEntity.setDescription("desc");
        cakeEntity.setImageUrl("http://cakeurl");
        cakeEntity.setName("cake1");
        cakeEntityList = Arrays.asList(cakeEntity);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCake() {
        when(cakeRepository.save(cakeEntity)).thenReturn(cakeEntity);
       assertNotNull(cakeDao.addCake(cakeEntity));
    }

    @Test
    void getCakeList() {
        when(cakeRepository.findAll()).thenReturn(cakeEntityList);
        assertNotNull(cakeDao.getCakeList());

    }
}