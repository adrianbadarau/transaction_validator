package com.for_the_love_of_code.transaction_validator.services;

import com.for_the_love_of_code.transaction_validator.domain.Transaction;
import com.for_the_love_of_code.transaction_validator.domain.TransactionDTO;
import com.for_the_love_of_code.transaction_validator.domain.ValidationResponse;
import com.for_the_love_of_code.transaction_validator.utils.CsvTransactionMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Validation service for transactions, will check if there are any transactions in the CSV file that have duplicate
 * references or balance < 0
*/
@Service
public class TransactionValidationService {

    /**
     * Main method to be used for validation, pass to this the file from the FE, and you will get a response DTO
    */
    public ValidationResponse validateTransactionUpload(MultipartFile csv) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setSuccess(true);
        var transactionReferences = new ArrayList<Integer>();

        var invalidTransactions = readCsv(csv).stream().filter(transactionDTO -> isInvalidTransaction(transactionDTO, transactionReferences)).collect(Collectors.toList());
        if (!invalidTransactions.isEmpty()) {
            validationResponse.setFailedItems(invalidTransactions);
            validationResponse.setSuccess(false);
        }

        return validationResponse;
    }

    @SneakyThrows
    // For brevity and clarity of code we use the above annotation
    private List<TransactionDTO> readCsv(MultipartFile csv) {
        try (var reader = new InputStreamReader(csv.getInputStream())) {
            return new CsvToBeanBuilder<Transaction>(reader).withType(Transaction.class).build().parse()
                    .stream().map(CsvTransactionMapper::convertToDTO).collect(Collectors.toList());
        }
    }

    private boolean isInvalidTransaction(TransactionDTO transaction, ArrayList<Integer> transactionReferences) {
        if (transactionReferences.contains(transaction.getReference())) {
            return true;
        }
        transactionReferences.add(transaction.getReference());
        return transaction.getEndBalance().compareTo(BigDecimal.ZERO) < 0;
    }
}
