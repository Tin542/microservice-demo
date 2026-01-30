package com.tinnt.finance_service.service;

import com.tinnt.finance_service.common.util.IncomeMapper;
import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.dto.response.DetailIncomeResponseDto;
import com.tinnt.finance_service.entity.IncomeEntity;
import com.tinnt.finance_service.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    IncomeRepository repository;

    @Autowired
    IncomeMapper mapper;

    @Override
    public CreateIncomeResponseDto createIncome(CreateIncomeRequestDto request) {
        IncomeEntity entity = repository.createIncome(request);
        CreateIncomeResponseDto response = new CreateIncomeResponseDto();

        if (response != null) {
            response.setIncomeCode(entity.getIncomeCode());
            response.setDate(entity.getDate());
        }
        
        return response;
    }

    @Override
    public DetailIncomeResponseDto getIncomeByCode(String incomeCode) {
        DetailIncomeResponseDto result = mapper.toDetailDto(repository.getIncomeByCode(incomeCode));
        return result;
    }
}
