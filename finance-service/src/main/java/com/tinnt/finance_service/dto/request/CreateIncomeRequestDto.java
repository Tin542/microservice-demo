package com.tinnt.finance_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIncomeRequestDto {

    private String amount;

    private String date;

    private String note;

    private String categoryCode;
}
