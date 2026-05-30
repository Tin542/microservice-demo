package com.tinnt.finance_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIncomeRequestDto {

    @NotBlank(message = "Amount is required")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Amount must be a valid number with up to 2 decimal places")
    private String amount;

    @NotBlank(message = "Date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in format yyyy-MM-dd")
    private String date;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;

    @NotBlank(message = "Category code is required")
    private String categoryCode;
}
