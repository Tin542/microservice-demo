package com.tinnt.finance_service.entity;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incomes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String incomeCode;

    @Description("Amount Income")
    private String amount;

    @Description("Date Income")
    private String date;

    @Description("Note")
    private String note;

    @Description("Category")
    private String categoryCode;
}
