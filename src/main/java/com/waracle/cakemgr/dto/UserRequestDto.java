package com.waracle.cakemgr.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {
    @NotBlank
    String userName;
    @NotBlank
    String password;
}
