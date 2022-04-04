package com.waracle.cakemgr.dao;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CakeDao {
    @Autowired
    CakeRepository cakeRepository;

    public CakeEntity addCake(CakeEntity cakeEntity) {
        log.debug("Request received to add cake: ", cakeEntity);
        CakeEntity cakeEntityResponse = cakeRepository.save(cakeEntity);
        log.debug("Cake added with ID: ", cakeEntityResponse.getId());
        return cakeEntityResponse;
    }

    public List<CakeEntity> getCakeList() {
        log.debug("Request received to list all cakes ");
        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        log.debug("Cake list returned : ", cakeEntityList);
        return cakeEntityList;
    }
}
