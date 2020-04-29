package com.for_the_love_of_code.transaction_validator.domain;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse {
    private List<Transaction> failedItems;
    private boolean success;
}
