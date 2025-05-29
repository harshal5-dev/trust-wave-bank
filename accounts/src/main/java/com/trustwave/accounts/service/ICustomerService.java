package com.trustwave.accounts.service;

import com.trustwave.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {


  /**
   * Fetches customer details based on the provided mobile number.
   *
   * @param mobileNumber the mobile number of the customer
   * @return CustomerDetailsDto containing customer, account, cards, and loans information
   */
  CustomerDetailsDto getCustomerDetails(String mobileNumber);
}
