package com.trustwave.cards.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Cards extends BaseModel {

  @Column(name = "mobile_number", unique = true, length = 15, nullable = false)
  private String mobileNumber;

  @Column(name = "card_number", unique = true, length = 100)
  private String cardNumber;

  @Column(name ="card_type", length = 100)
  private String cardType;

  @Column(name = "total_limit", nullable = false)
  private Double totalLimit;

  @Column(name = "amount_used", nullable = false)
  private Double amountUsed;

  @Column(name = "available_amount", nullable = false)
  private Double availableAmount;
}
