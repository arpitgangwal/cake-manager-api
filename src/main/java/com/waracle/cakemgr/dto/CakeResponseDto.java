package com.waracle.cakemgr.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CakeResponseDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String imageUrl;
}
