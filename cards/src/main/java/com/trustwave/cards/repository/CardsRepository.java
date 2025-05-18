package com.trustwave.cards.repository;

import com.trustwave.cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardsRepository extends JpaRepository<Cards, UUID> {
  Optional<Cards> findByMobileNumber(String mobileNumber);
  Optional<Cards> findByCardNumber(String cardNumber);
}
