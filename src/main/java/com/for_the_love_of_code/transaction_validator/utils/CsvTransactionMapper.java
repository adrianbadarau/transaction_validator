package com.for_the_love_of_code.transaction_validator.utils;


import com.for_the_love_of_code.transaction_validator.domain.Transaction;
import com.for_the_love_of_code.transaction_validator.domain.TransactionDTO;
import lombok.val;

import java.math.BigDecimal;

public class CsvTransactionMapper {

    public static TransactionDTO convertToDTO(Transaction transaction) {
        val dto = new TransactionDTO();
        dto.setReference(Integer.valueOf(transaction.getReference()));
        dto.setDescription(transaction.getDescription());
        dto.setAccountNumber(transaction.getAccountNumber());
        dto.setStartBalance(new BigDecimal(transaction.getStartBalance()));
        dto.setMutation(new BigDecimal(transaction.getMutation()));
        dto.setEndBalance(new BigDecimal(transaction.getEndBalance()));
        return dto;
    }
}
