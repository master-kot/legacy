package com.nikolay.legacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {

    private Long id;
    private String action;
    private Long eventTime;
}
