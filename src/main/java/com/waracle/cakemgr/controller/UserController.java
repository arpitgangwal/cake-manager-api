package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.UserRequestDto;
import com.waracle.cakemgr.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping(path = "/jwt-token")
   public String generateToken(@RequestBody UserRequestDto user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
                return jwtUtil.generateToken(user.getUserName());
    }
}
