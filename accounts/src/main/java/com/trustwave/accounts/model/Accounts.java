package com.trustwave.accounts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class Accounts extends BaseModel {

  @Column(name = "account_number")
  private Long accountNumber;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "branch_address")
  private String branchAddress;

  @Column(name = "customer_id")
  private UUID customerId;
}
