package com.trustwave.accounts.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trustwave.accounts.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  Optional<Customer> findByMobileNumber(String mobileNumber);
}
