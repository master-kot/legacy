package com.nikolay.legacy.criterias;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BusinessDataSearchCriteria {

    private final Long id;
    private final String type;
    private final String businessValue;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
