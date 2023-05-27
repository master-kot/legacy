package com.nikolay.legacy.controller;

import com.nikolay.legacy.criteria.BusinessDataSearchCriteria;
import com.nikolay.legacy.dto.BusinessDataDto;
import com.nikolay.legacy.dto.BusinessDataRequest;
import com.nikolay.legacy.service.BusinessDataService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/data", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
@Api(value = "/api/v1/data", tags = {"Контроллер Бизнес данных"})
public class BusinessDataController {

    private final BusinessDataService businessDataService;

    @ApiOperation(value = "Поиск бизнес данных по параметрам")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно"),
            @ApiResponse(code = 404, message = "Не найдено")
    })
    @GetMapping("")
    public ResponseEntity<List<BusinessDataDto>> getAllDataBySearchCriteria(
            @ApiParam(value = "Идентификатор") @RequestParam(required = false) Long id,
            @ApiParam(value = "Тип") @RequestParam(required = false) String type,
            @ApiParam(value = "Данные") @RequestParam(required = false) String businessValue,
            @ApiParam(value = "Создан") @RequestParam(required = false) Long createdAt,
            @ApiParam(value = "Обновлен") @RequestParam(required = false) Long updatedAt,
            @ApiParam(value = "Номер страницы") @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "Размер страницы") @RequestParam(required = false, defaultValue = "0") Integer pageSize
    ) {
        return ResponseEntity.ok(
                businessDataService.getAllDtoBySearchCriteria(
                        BusinessDataSearchCriteria.builder()
                                .id(id)
                                .type(type)
                                .businessValue(businessValue)
                                .createdAt(createdAt)
                                .updatedAt(updatedAt)
                                .build(),
                        PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id"))
                );
    }

    @ApiOperation(value = "Поиск бизнес данных по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно"),
            @ApiResponse(code = 404, message = "Не найдено")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BusinessDataDto> getDataById(@ApiParam("Идентификатор") @PathVariable Long id) {
        return ResponseEntity.ok(businessDataService.getById(id));
    }

    @ApiOperation(value = "Добавление бизнес данных")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно")})
    @PostMapping("")
    public ResponseEntity<BusinessDataDto> postData(
            @ApiParam(value = "Данные") @RequestBody BusinessDataRequest request) {
        return ResponseEntity.ok(businessDataService.save(request));
    }
}
