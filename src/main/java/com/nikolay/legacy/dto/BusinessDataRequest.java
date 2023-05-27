package com.nikolay.legacy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Запрос для регистрации нового Сообщения")
public class BusinessDataRequest {

    @ApiModelProperty(notes = "Тип данных", dataType = "String", required = true)
    private String type;

    @ApiModelProperty(notes = "Данные", dataType = "String", required = true, position = 1)
    private String businessValue;
}
