package com.example.netology_diplom_moneytransferservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.netology_diplom_moneytransferservice.dto.Amount;
import com.example.netology_diplom_moneytransferservice.dto.TransferResponse;
import com.example.netology_diplom_moneytransferservice.exception.CardInvalidCvvException;
import com.example.netology_diplom_moneytransferservice.exception.CardInvalidDateException;
import com.example.netology_diplom_moneytransferservice.exception.CardNumberNotFoundException;
import com.example.netology_diplom_moneytransferservice.exception.NotEnoughMoneyException;
import com.example.netology_diplom_moneytransferservice.model.Card;
import com.example.netology_diplom_moneytransferservice.repositories.CardHolderRepository;

import java.util.UUID;

@Service
@Transactional
public class TransferService {
    private final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private final CardHolderRepository cardHolderRepository;

    public TransferService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }

    public void transferMoney(long cardFromNumber, String cardFromValidTill, String cardFromCVV, long cardToNumber, Amount amount) {
        logger.info("Начало транзакции");
        Card sender = cardHolderRepository.findCardByNumber(cardFromNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Карта отправителя " + cardFromNumber + " не найдена"));

        Card receiver = cardHolderRepository.findCardByNumber(cardToNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Карта получателя " + cardToNumber + " не найдена"));

        if (!cardFromValidTill.equals(sender.getValid())) {
            throw new CardInvalidDateException("Неверная дата действия карты");
        }
        if (!cardFromCVV.equals(sender.getCvv())) {
            throw new CardInvalidCvvException("Неверный код cvv карты");
        }
        if (amount.getValue() > sender.getAmount()) {
            throw new NotEnoughMoneyException("Недостаточно средств для перевода на счете карты");
        }

        int senderNewAmount = sender.getAmount() - amount.getValue();
        int receiverNewAmount = receiver.getAmount() + amount.getValue();

        cardHolderRepository.changeAmount(cardFromNumber, senderNewAmount);
        cardHolderRepository.changeAmount(cardToNumber, receiverNewAmount);
        logger.info("Перевод с карты " + cardFromNumber + " на карту " + cardToNumber
                + " в сумме " + amount.getValue() + amount.getCurrency() + " успешно осуществлен.");
    }

    public TransferResponse transferResponse() {
        TransferResponse confirmOperation = new TransferResponse(UUID.randomUUID().toString());
        logger.info("ID операции: " + confirmOperation.getOperationId());
        return confirmOperation;
    }
}