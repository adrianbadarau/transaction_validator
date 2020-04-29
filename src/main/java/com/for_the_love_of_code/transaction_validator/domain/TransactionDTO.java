package com.for_the_love_of_code.transaction_validator.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private Integer reference;
    private String accountNumber;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

}
