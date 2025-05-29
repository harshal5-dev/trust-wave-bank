package com.trustwave.accounts.service.impl;

import com.trustwave.accounts.dto.AccountsDto;
import com.trustwave.accounts.dto.CardsDto;
import com.trustwave.accounts.dto.CustomerDetailsDto;
import com.trustwave.accounts.dto.LoansDto;
import com.trustwave.accounts.exception.ResourceNotFoundException;
import com.trustwave.accounts.mapper.AccountMapper;
import com.trustwave.accounts.mapper.CustomerMapper;
import com.trustwave.accounts.model.Accounts;
import com.trustwave.accounts.model.Customer;
import com.trustwave.accounts.repository.AccountsRepository;
import com.trustwave.accounts.repository.CustomerRepository;
import com.trustwave.accounts.service.ICustomerService;
import com.trustwave.accounts.service.client.CardsFeignClient;
import com.trustwave.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  private final CardsFeignClient cardsFeignClient;
  private final LoansFeignClient loansFeignClient;

  /**
   * Fetches customer details based on the provided mobile number.
   *
   * @param mobileNumber the mobile number of the customer
   * @return CustomerDetailsDto containing customer, account, cards, and loans information
   */
  @Override
  public CustomerDetailsDto getCustomerDetails(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Accounts accounts = accountsRepository.findByCustomerId(customer.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString()));

    CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
    customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

    ResponseEntity<LoansDto> loansDtoResEntity = loansFeignClient.fetchLoan(mobileNumber);
    customerDetailsDto.setLoansDto(loansDtoResEntity.getBody());

    ResponseEntity<CardsDto> cardsDtoResEntity = cardsFeignClient.fetchCard(mobileNumber);
    customerDetailsDto.setCardsDto(cardsDtoResEntity.getBody());

    return customerDetailsDto;
  }
}
