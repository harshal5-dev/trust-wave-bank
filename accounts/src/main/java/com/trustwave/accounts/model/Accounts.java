package com.trustwave.accounts.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

  @Column(name = "communication_sw")
  private Boolean communicationSw;

  @Column(name = "customer_id")
  private UUID customerId;
}
