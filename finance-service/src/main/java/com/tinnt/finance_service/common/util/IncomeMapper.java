package com.tinnt.finance_service.common.util;

import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.dto.response.DetailIncomeResponseDto;
import com.tinnt.finance_service.entity.IncomeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    DetailIncomeResponseDto toDetailDto(IncomeEntity entity);
    CreateIncomeResponseDto toCreateDto(IncomeEntity entity);
}
