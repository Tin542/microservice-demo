package com.tinnt.finance_service.repository;

import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.entity.IncomeEntity;

public interface IncomeRepository {
    IncomeEntity creatIncome(CreateIncomeRequestDto request);

}
