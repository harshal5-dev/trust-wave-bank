package com.trustwave.accounts.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.trustwave.accounts.model.Accounts;
import jakarta.transaction.Transactional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, UUID> {
  Optional<Accounts> findByCustomerId(UUID customerId);

  Optional<Accounts> findByAccountNumber(Long accountNumber);

  @Transactional
  @Modifying
  void deleteByCustomerId(UUID id);
}
