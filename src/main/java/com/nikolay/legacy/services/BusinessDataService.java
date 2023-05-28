package com.nikolay.legacy.services;

import com.nikolay.legacy.criterias.BusinessDataSearchCriteria;
import com.nikolay.legacy.dtos.BusinessDataResponse;
import com.nikolay.legacy.dtos.BusinessDataCreateRequest;
import com.nikolay.legacy.dtos.BusinessDataUpdateRequest;
import com.nikolay.legacy.dtos.MessageDto;
import com.nikolay.legacy.entities.BusinessData;
import com.nikolay.legacy.enums.EventType;
import com.nikolay.legacy.exceptions.DataNotFoundException;
import com.nikolay.legacy.mappers.BusinessDataMapper;
import com.nikolay.legacy.daos.BusinessDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.nikolay.legacy.criterias.BusinessDataSpecifications.eventHasSearchCriteria;
import static com.nikolay.legacy.helpers.Constants.DATA_NOT_FOUND_BY_ID;
import static java.time.temporal.ChronoField.INSTANT_SECONDS;

@Service
@RequiredArgsConstructor
public class BusinessDataService {

    @Value(value = "${spring.kafka.topic-name}")
    private String topicName;

    private final BusinessDataRepository businessDataRepository;
    private final BusinessDataMapper businessDataMapper;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public List<BusinessDataResponse>  getAllDtoBySearchCriteria(BusinessDataSearchCriteria criteria,
                                                                 Pageable pageable) {
        return businessDataMapper.mapEntityToDto(
                businessDataRepository.findAll(eventHasSearchCriteria(criteria), pageable).toList());
    }

    public BusinessDataResponse getById(Long id) {
        return businessDataRepository.findById(id).map(businessDataMapper::mapEntityToDto)
                        .orElseThrow(() -> new DataNotFoundException(String.format(DATA_NOT_FOUND_BY_ID, id)));
    }

    @Transactional
    public BusinessDataResponse save(BusinessDataCreateRequest request) {
        BusinessData data = businessDataRepository.save(businessDataMapper.mapCreateRequestToEntity(request));
        sendMessage(data.getId(), EventType.CREATE);
        return businessDataMapper.mapEntityToDto(data);
    }

    @Transactional
    public void delete(Long id) {
        businessDataRepository.deleteById(id);
        sendMessage(id, EventType.UPDATE);
    }

    @Transactional
    public void update(BusinessDataUpdateRequest request) {
        BusinessData data = businessDataRepository.findById(request.getId())
                .map(s -> businessDataMapper.updateEntity(s, request))
                .orElse(businessDataMapper.mapUpdateRequestToEntity(request));

        data = businessDataRepository.save(data);
        sendMessage(data.getId(), EventType.UPDATE);
    }

    private void sendMessage(Long id, EventType action) {
        kafkaTemplate.send(topicName, new MessageDto(id, action.toString(), Instant.now().getLong(INSTANT_SECONDS)));
    }
}
