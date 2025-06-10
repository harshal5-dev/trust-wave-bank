package com.trustwave.loans.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "loans")
@ToString
public class Loans extends  BaseModel {

  @Column(name = "mobile_number", nullable = false,length = 15)
  private String mobileNumber;

  @Column(name = "loan_number", nullable = false,length = 100)
  private String loanNumber;

  @Column(name = "loan_type", nullable = false,length = 100)
  private String loanType;

  @Column(name = "total_loan", nullable = false)
  private Double totalLoan;

  @Column(name = "amount_paid", nullable = false)
  private Double amountPaid;

  @Column(name = "outstanding_amount", nullable = false)
  private Double outstandingAmount;
}
