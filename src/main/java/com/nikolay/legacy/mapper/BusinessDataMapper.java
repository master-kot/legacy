package com.nikolay.legacy.mapper;

import com.nikolay.legacy.dto.BusinessDataDto;
import com.nikolay.legacy.entity.BusinessData;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessDataMapper {

    BusinessDataDto mapEntityToDto(BusinessData entity);

    List<BusinessDataDto> mapEntityToDto(List<BusinessData> entities);
}
