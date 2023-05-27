package com.nikolay.legacy.mapper;

import com.nikolay.legacy.dto.BusinessDataDto;
import com.nikolay.legacy.dto.BusinessDataRequest;
import com.nikolay.legacy.entity.BusinessData;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessDataMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "type", source = "entity.type"),
            @Mapping(target = "businessValue", source = "entity.businessValue"),
            @Mapping(target = "createdAt", source = "entity.createdAt"),
            @Mapping(target = "updatedAt", source = "entity.updatedAt"),

    })
    BusinessDataDto mapEntityToDto(BusinessData entity);

    default BusinessDataDto mapEntityToDto(Optional<BusinessData> optional) {
        return optional.map(this::mapEntityToDto).orElse(null);
    }

    default BusinessData mapRequestToEntity(BusinessDataRequest request) {
        return BusinessData.builder()
                .type(request.getType())
                .businessValue(request.getBusinessValue())
                .createdAt(Instant.now().getEpochSecond())
                .updatedAt(Instant.now().getEpochSecond())
                .build();
    }

    List<BusinessDataDto> mapEntityToDto(List<BusinessData> entities);
}
