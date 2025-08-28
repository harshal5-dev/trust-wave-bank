package com.trustwave.accounts.service.impl;

import com.trustwave.accounts.constants.AccountsConstants;
import com.trustwave.accounts.dto.AccountsDto;
import com.trustwave.accounts.dto.AccountsMsgDto;
import com.trustwave.accounts.dto.CustomerDto;
import com.trustwave.accounts.exception.CustomerAlreadyExistsException;
import com.trustwave.accounts.exception.ResourceNotFoundException;
import com.trustwave.accounts.mapper.AccountMapper;
import com.trustwave.accounts.mapper.CustomerMapper;
import com.trustwave.accounts.model.Accounts;
import com.trustwave.accounts.model.Customer;
import com.trustwave.accounts.repository.AccountsRepository;
import com.trustwave.accounts.repository.CustomerRepository;
import com.trustwave.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountsService {

  private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  private final StreamBridge streamBridge;

  /**
   * This method is used to create a new customer.
   *
   * @param customerDto - CustomerDto object containing customer details
   */
  @Override
  public void createCustomer(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber " +
          customerDto.getMobileNumber());
    }

    Customer savedCustomer = customerRepository.save(customer);
    Accounts savedAccounts = accountsRepository.save(createNewAccount(savedCustomer));
    // sendCommunication(savedAccounts, savedCustomer);
  }

  private void sendCommunication(Accounts accounts, Customer customer) {
    var accountsMsgDto = new AccountsMsgDto(accounts.getAccountNumber(), customer.getFullName(), customer.getEmail(), customer.getMobileNumber());
    logger.info("Sending communication request for the details: {}", accountsMsgDto);
    var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
    logger.info("Is the Communication request sent successfully triggered?: {}", result);
  }

  /**
   * This method is used to create a new account for the customer.
   *
   * @param customer - CustomerDto object containing customer details
   * @return - Accounts object containing account details
   */
  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);

    return newAccount;
  }

  /**
   * This method is used to get the customer details by mobile number.
   *
   * @param mobileNumber - Mobile number of the customer
   * @return - CustomerDto object containing customer details
   */
  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Accounts accounts = accountsRepository.findByCustomerId(customer.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString()));

    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

    return customerDto;
  }

  /**
   * This method is used to update accounts for the customer.
   *
   * @param customerDto - CustomerDto object containing customer details
   * @return - boolean indicating success or failure of the update operation
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();

    if (accountsDto != null) {
      Accounts accounts = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber())
          .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));

      AccountMapper.mapToAccounts(accountsDto, accounts);
      accounts = accountsRepository.save(accounts);

      UUID customerId = accounts.getCustomerId();

      Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

      CustomerMapper.mapToCustomer(customerDto, customer);
      customerRepository.save(customer);
      isUpdated = true;
    }

    return isUpdated;
  }

  /**
   * This method is used to delete the account for the customer.
   *
   * @param mobileNumber - Mobile number of the customer
   * @return - boolean indicating success or failure of the delete operation
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    accountsRepository.deleteByCustomerId(customer.getId());
    customerRepository.deleteById(customer.getId());
    return true;
  }

  /**
   * This method is used to update the communication status of the account.
   *
   * @param accountNumber - Account number of the customer
   * @return - boolean indicating success or failure of the update operation
   */
  @Override
  public boolean updateCommunicationStatus(Long accountNumber) {
    boolean isUpdated = false;

    if (accountNumber != null) {
      Accounts accounts = accountsRepository.findByAccountNumber(accountNumber)
          .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString()));

      accounts.setCommunicationSw(true);
      accountsRepository.save(accounts);
      isUpdated = true;
    }

    return isUpdated;
  }

}
