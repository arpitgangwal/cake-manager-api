package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.UserRequestDto;
import com.waracle.cakemgr.util.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
   @InjectMocks
   UserController userController;
   @Mock
   AuthenticationManager authenticationManager;
   @Mock
   UserRequestDto userRequestDto;
   @Mock
    org.springframework.security.core.Authentication authentication;
   @Mock
    JwtUtil jwtUtil;
   private String jwtToken = "fdjhgdjhgdjkgndjngjgdjgbd";
    @Test
    void generateToken() {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userRequestDto.getUserName()).thenReturn("user1");
        when(userRequestDto.getPassword()).thenReturn("pwd1");
        when(jwtUtil.generateToken(any())).thenReturn(jwtToken);
        assertThat(userController.generateToken(userRequestDto)).isNotBlank();
    }
}