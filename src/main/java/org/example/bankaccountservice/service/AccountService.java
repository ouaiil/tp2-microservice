package org.example.bankaccountservice.service;

import org.example.bankaccountservice.dto.BankAccountRequestDTO;
import org.example.bankaccountservice.dto.BankAccountResponseDTO;
import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.enums.AccountType;

public interface AccountService {
     BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
