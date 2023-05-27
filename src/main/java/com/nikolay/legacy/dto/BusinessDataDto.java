package com.nikolay.legacy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Бизнес данные")
public class BusinessDataDto {

    private Long id;
    private String type;
    private String businessValue;
    private Long createdAt;
    private Long updatedAt;
}
