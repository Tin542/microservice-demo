package com.tinnt.finance_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailIncomeResponseDto {
    private String incomeCode;
    private String amount;
    private String date;
    private String note;
}
