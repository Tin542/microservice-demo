package com.tinnt.finance_service.service;

import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.entity.IncomeEntity;
import com.tinnt.finance_service.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    IncomeRepository repository;

    @Override
    public CreateIncomeResponseDto createIncome(CreateIncomeRequestDto request) {
        IncomeEntity enitty = repository.creatIncome(request);
        CreateIncomeResponseDto response = new CreateIncomeResponseDto();

        if (response != null) {
            response.setIncomeCode(enitty.getIncomeCode());
            response.setDate(enitty.getDate());
        }
        
        return response;
    }
}
