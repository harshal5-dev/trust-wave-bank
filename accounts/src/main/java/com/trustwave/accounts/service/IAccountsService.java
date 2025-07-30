package com.trustwave.accounts.service;

import com.trustwave.accounts.dto.CustomerDto;

public interface IAccountsService {

  /**
   * This method is used to create a new customer.
   *
   * @param customerDto - CustomerDto object containing customer details
   */
  void createCustomer(CustomerDto customerDto);

  /**
   * This method is used to get the customer details by mobile number.
   *
   * @param mobileNumber - Mobile number of the customer
   * @return - CustomerDto object containing customer details
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   * This method is used to update accounts for the customer.
   *
   * @param customerDto - CustomerDto object containing customer details
   * @return - boolean indicating success or failure of the update operation
   */
  boolean updateAccount(CustomerDto customerDto);


  /**
   * This method is used to delete the account for the customer.
   *
   * @param mobileNumber - Mobile number of the customer
   * @return - boolean indicating success or failure of the delete operation
   */
  boolean deleteAccount(String mobileNumber);


  /**
   * This method is used to update the communication status of the account.
   *
   * @param accountNumber - Account number of the customer
   * @return - boolean indicating success or failure of the update operation
   */
  boolean updateCommunicationStatus(Long accountNumber);
}
