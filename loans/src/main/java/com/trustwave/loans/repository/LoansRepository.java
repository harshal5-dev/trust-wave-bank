package com.trustwave.loans.repository;

import com.trustwave.loans.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoansRepository extends JpaRepository<Loans, UUID> {
  Optional<Loans> findByMobileNumber(String mobileNumber);
  Optional<Loans> findByLoanNumber(String loanNumber);
}
