package com.example.netology_diplom_moneytransferservice.dto;

public class TransferResponse {
    private String operationId;

    public TransferResponse(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}