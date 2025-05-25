package com.trustwave.loans.service.impl;

import com.trustwave.loans.constants.LoansConstants;
import com.trustwave.loans.dto.LoansDto;
import com.trustwave.loans.exception.LoanAlreadyExistsException;
import com.trustwave.loans.exception.ResourceNotFoundException;
import com.trustwave.loans.mapper.LoansMapper;
import com.trustwave.loans.model.Loans;
import com.trustwave.loans.repository.LoansRepository;
import com.trustwave.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansService implements ILoansService {
  private final LoansRepository loansRepository;

  /**
   * @param mobileNumber - Mobile Number of the Customer
   */
  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);

    if (loans.isPresent()) {
      throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
    }

    Loans newLoans = createNewLoan(mobileNumber);
    loansRepository.save(newLoans);
  }

  /**
   * @param mobileNumber - Mobile Number of the Customer
   * @return the new loan details
   */
  private Loans createNewLoan(String mobileNumber) {
    Loans newLoans = new Loans();
    long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);

    newLoans.setLoanNumber(String.valueOf(randomLoanNumber));
    newLoans.setMobileNumber(mobileNumber);
    newLoans.setLoanType(LoansConstants.HOME_LOAN);
    newLoans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
    newLoans.setAmountPaid(0.0);
    newLoans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

    return newLoans;
  }

  /**
   * @param mobileNumber - Input mobile Number
   * @return Loan Details based on a given mobileNumber
   */
  @Override
  public LoansDto fetchLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    return LoansMapper.mapToLoansDto(loans, new LoansDto());
  }

  /**
   * @param loansDto - LoansDto Object
   * @return boolean indicating if the update of card details is successful or not
   */
  @Override
  public boolean updateLoan(LoansDto loansDto) {
    Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber()));

    LoansMapper.mapToLoans(loansDto, loans);
    loansRepository.save(loans);

    return true;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of loan details is successful or not
   */
  @Override
  public boolean deleteLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

    loansRepository.deleteById(loans.getId());
    return true;
  }
}
