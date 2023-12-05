package com.example.netology_diplom_moneytransferservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.netology_diplom_moneytransferservice.dto.Amount;
import com.example.netology_diplom_moneytransferservice.model.Card;
import com.example.netology_diplom_moneytransferservice.repositories.CardHolderRepository;
import com.example.netology_diplom_moneytransferservice.service.TransferService;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransferServiceSpringIntegrationTest {

    @MockBean
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private TransferService transferService;

    @Test
    void transferServiceTransferMoneyTest() {
        Card sender = new Card();
        sender.setNumber(4000);
        sender.setValid("1111");
        sender.setCvv("333");
        sender.setAmount(1000);
        sender.setCurrency("RUB");

        Card receiver = new Card();
        receiver.setNumber(5000);
        receiver.setAmount(1000);

        Amount amount = new Amount(100, "RUB");

        when(cardHolderRepository.findCardByNumber(sender.getNumber())).thenReturn(Optional.of(sender));
        when(cardHolderRepository.findCardByNumber(receiver.getNumber())).thenReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getNumber(), sender.getValid(), sender.getCvv(), receiver.getNumber(), amount);

        verify(cardHolderRepository).changeAmount(sender.getNumber(), 900);
        verify(cardHolderRepository).changeAmount(receiver.getNumber(), 1100);
    }
}