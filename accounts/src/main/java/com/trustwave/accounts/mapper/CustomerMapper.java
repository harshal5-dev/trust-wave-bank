package com.trustwave.accounts.mapper;

import com.trustwave.accounts.dto.CustomerDto;
import com.trustwave.accounts.model.Customer;

public class CustomerMapper {
  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setFullName(customer.getFullName());
    customerDto.setEmail(customer.getEmail());
    customerDto.setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
    customer.setFullName(customerDto.getFullName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    return customer;
  }
}
