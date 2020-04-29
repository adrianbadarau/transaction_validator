package com.for_the_love_of_code.transaction_validator.controllers;

import com.for_the_love_of_code.transaction_validator.domain.ValidationResponse;
import com.for_the_love_of_code.transaction_validator.services.TransactionValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/transactions/validate")
public class TransactionValidationController {

    private TransactionValidationService transactionValidationService;

    public TransactionValidationController(TransactionValidationService transactionValidationService) {
        this.transactionValidationService = transactionValidationService;
    }

    @PostMapping("/")
    public ResponseEntity<ValidationResponse> validateTransactions(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(this.transactionValidationService.validateTransactionUpload(file));
    }
}
