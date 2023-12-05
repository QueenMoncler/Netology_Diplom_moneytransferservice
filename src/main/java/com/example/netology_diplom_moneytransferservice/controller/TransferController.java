package com.example.netology_diplom_moneytransferservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.netology_diplom_moneytransferservice.dto.Amount;
import com.example.netology_diplom_moneytransferservice.dto.ConfirmResponse;
import com.example.netology_diplom_moneytransferservice.dto.TransferRequest;
import com.example.netology_diplom_moneytransferservice.dto.TransferResponse;
import com.example.netology_diplom_moneytransferservice.service.TransferService;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io/card-transfer/", allowedHeaders = "*")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("transfer")
    @ResponseBody
    public TransferResponse transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getCardFromNumber(),
                request.getCardFromValidTill(),
                request.getCardFromCVV(),
                request.getCardToNumber(),
                new Amount(request.getAmount().getValue(), request.getAmount().getCurrency())
        );
        TransferResponse confirmOperation = transferService.transferResponse();
        return confirmOperation;
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<ConfirmResponse> confirmOperation(@RequestBody ConfirmResponse confirmRequest) {
        confirmRequest.getOperationId();
        confirmRequest.getCode();
        return ResponseEntity.ok().body(confirmRequest);
    }
}