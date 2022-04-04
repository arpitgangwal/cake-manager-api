package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
     UserEntity findByUserName(String userName);
}
