package com.trustwave.loans.mapper;

import com.trustwave.loans.dto.LoansDto;
import com.trustwave.loans.model.Loans;

public final class LoansMapper {

  public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
    loansDto.setLoanNumber(loans.getLoanNumber());
    loansDto.setLoanType(loans.getLoanType());
    loansDto.setMobileNumber(loans.getMobileNumber());
    loansDto.setTotalLoan(loans.getTotalLoan());
    loansDto.setAmountPaid(loans.getAmountPaid());
    loansDto.setOutstandingAmount(loans.getOutstandingAmount());
    return loansDto;
  }

  public static void mapToLoans(LoansDto loansDto, Loans loans) {
    loans.setLoanNumber(loansDto.getLoanNumber());
    loans.setLoanType(loansDto.getLoanType());
    loans.setMobileNumber(loansDto.getMobileNumber());
    loans.setTotalLoan(loansDto.getTotalLoan());
    loans.setAmountPaid(loansDto.getAmountPaid());
    loans.setOutstandingAmount(loansDto.getOutstandingAmount());
  }

}
