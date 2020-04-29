package com.for_the_love_of_code.transaction_validator.services;

import com.for_the_love_of_code.transaction_validator.domain.Transaction;
import com.for_the_love_of_code.transaction_validator.domain.ValidationResponse;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionValidationService {

    public ValidationResponse validateTransactionUpload(MultipartFile csv) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setSuccess(true);

        var invalidTransactions = readCsv(csv).stream().filter(this::isInvalidTransaction);
        if (invalidTransactions.count() > 0) {
            validationResponse.setFailedItems(invalidTransactions.collect(Collectors.toList()));
            validationResponse.setSuccess(false);
        }

        return validationResponse;
    }

    @SneakyThrows
    private List<Transaction> readCsv(MultipartFile csv) {
        try (var reader = new InputStreamReader(csv.getInputStream())) {
            var items = new CsvToBeanBuilder<Transaction>(reader).withType(Transaction.class).build().parse();
            return items;
        }
    }

    private boolean isInvalidTransaction(Transaction transaction) {
        val transactionIds = new ArrayList<String>();
        transaction.equals(new Transaction());
        return true;
    }
}
