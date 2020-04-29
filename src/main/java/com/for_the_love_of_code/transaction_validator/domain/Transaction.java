package com.for_the_love_of_code.transaction_validator.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Transaction {
    @CsvBindByName(column = "Reference")
    private Integer reference;
    @CsvBindByName(column = "Account Number")
    private String accountNumber;
    @CsvBindByName(column = "Description")
    private String description;
    @CsvBindByName(column = "Start Balance", locale = "nl-NL")
    private String startBalance;
    @CsvBindByName(column = "Mutation", locale = "nl-NL")
    private String mutation;
    @CsvBindByName(column = "End Balance", locale = "nl-NL")
    private String endBalance;
}
