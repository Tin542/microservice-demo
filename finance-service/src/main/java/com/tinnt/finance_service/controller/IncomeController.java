package com.tinnt.finance_service.controller;

import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @PostMapping("")
    public ResponseEntity<?> createIncome(@RequestBody CreateIncomeRequestDto dto) {
        CreateIncomeResponseDto result = incomeService.createIncome(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
