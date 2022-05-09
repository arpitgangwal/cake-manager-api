package com.waracle.cakemgr.util.jwt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
@TestPropertySource("classpath:application.properties")
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;
    private UserDetails userDetails;
    private String jwt;

    @BeforeEach
    void setUp() {

        jwt = jwtUtil.generateToken("user1");
        userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "user1";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void extractUsername() {
        assertEquals(jwtUtil.extractUsername(jwt), "user1");
    }

    @Test
    void generateToken() {
        assertNotNull(jwt);
    }

    @Test
    void validateToken() {
        assertTrue(jwtUtil.validateToken(jwt, userDetails));
    }
}