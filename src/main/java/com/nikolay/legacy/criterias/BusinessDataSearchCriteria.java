package com.nikolay.legacy.criterias;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessDataSearchCriteria {

    private final Long id;
    private final String type;
    private final String businessValue;
    private final Long createdAt;
    private final Long updatedAt;
}
