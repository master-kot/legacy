package com.nikolay.legacy.service;

import com.nikolay.legacy.criteria.BusinessDataSearchCriteria;
import com.nikolay.legacy.dto.BusinessDataDto;
import com.nikolay.legacy.mapper.BusinessDataMapper;
import com.nikolay.legacy.repository.BusinessDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nikolay.legacy.criteria.BusinessDataSpecifications.eventHasSearchCriteria;

@Service
@RequiredArgsConstructor
public class BusinessDataService {

    private final BusinessDataRepository businessDataRepository;
    private final BusinessDataMapper businessDataMapper;

    public List<BusinessDataDto>  getAllDtoBySearchCriteria(BusinessDataSearchCriteria criteria,
                                                            Pageable pageable) {
        return businessDataMapper.mapEntityToDto(
                businessDataRepository.findAll(eventHasSearchCriteria(criteria), pageable).toList());
    }
}
