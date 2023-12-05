package com.example.netology_diplom_moneytransferservice.dto;

public class TransferRequest {
    private long cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
    private long cardToNumber;
    private Amount amount;

    public TransferRequest(long cardFromNumber, String cardFromValidTill, String cardFromCVV, long cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public long getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(long cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public long getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(long cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}