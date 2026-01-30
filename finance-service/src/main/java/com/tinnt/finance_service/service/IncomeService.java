package com.tinnt.finance_service.service;

import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.dto.response.DetailIncomeResponseDto;


public interface IncomeService {
    CreateIncomeResponseDto createIncome(CreateIncomeRequestDto request);
    DetailIncomeResponseDto getIncomeByCode(String incomeCode);
}
