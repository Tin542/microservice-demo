package com.tinnt.finance_service.controller;

import com.tinnt.finance_service.common.constant.Constants;
import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.dto.response.CreateIncomeResponseDto;
import com.tinnt.finance_service.dto.response.DetailIncomeResponseDto;
import com.tinnt.finance_service.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.ENDPOINT + "/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @PostMapping("")
    public ResponseEntity<?> createIncome(@Valid @RequestBody CreateIncomeRequestDto dto) {
        CreateIncomeResponseDto result = incomeService.createIncome(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{incomeCode}")
    public ResponseEntity<?> getDetailIncome(@PathVariable("incomeCode") String incomeCode){
        DetailIncomeResponseDto result = incomeService.getIncomeByCode(incomeCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
