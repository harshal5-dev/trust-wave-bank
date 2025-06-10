package com.trustwave.accounts.mapper;

import com.trustwave.accounts.dto.AccountsDto;
import com.trustwave.accounts.model.Accounts;

public final class AccountMapper {

  public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
    accountsDto.setAccountNumber(accounts.getAccountNumber());
    accountsDto.setAccountType(accounts.getAccountType());
    accountsDto.setBranchAddress(accounts.getBranchAddress());
    return accountsDto;
  }

  public static void mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
    accounts.setAccountNumber(accountsDto.getAccountNumber());
    accounts.setAccountType(accountsDto.getAccountType());
    accounts.setBranchAddress(accountsDto.getBranchAddress());
  }
}
