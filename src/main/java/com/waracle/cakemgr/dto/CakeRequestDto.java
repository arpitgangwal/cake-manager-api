package com.waracle.cakemgr.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@JsonAutoDetect
@Data
public class CakeRequestDto {
    @NotBlank
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    @NotBlank
    private String description;
    @JsonProperty("imageUrl")
    @NotBlank
    private String imageUrl;
}