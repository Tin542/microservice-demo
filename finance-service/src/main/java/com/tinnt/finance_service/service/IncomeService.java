package com.tinnt.finance_service.service;

import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;


public interface IncomeService {
    CreateIncomeResponseDto createIncome(CreateIncomeRequestDto request);
}
