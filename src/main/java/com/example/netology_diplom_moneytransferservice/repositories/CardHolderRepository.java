package com.example.netology_diplom_moneytransferservice.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.netology_diplom_moneytransferservice.model.Card;

import java.util.Optional;

public interface CardHolderRepository extends CrudRepository<Card, Long> {
    @Query("SELECT * FROM card WHERE number = :number")
    Optional<Card> findCardByNumber(long number);

    @Modifying
    @Query("UPDATE card SET amount = :amount WHERE number = :number")
    void changeAmount(long number, int amount);
}