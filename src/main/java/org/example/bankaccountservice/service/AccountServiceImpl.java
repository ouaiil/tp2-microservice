package org.example.bankaccountservice.service;

import org.example.bankaccountservice.dto.BankAccountRequestDTO;
import org.example.bankaccountservice.dto.BankAccountResponseDTO;
import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.mappers.AccountMapper;
import org.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .ceratedAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency((bankAccountDTO.getCurrency()))
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
         BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);

        return bankAccountResponseDTO;

    }
}
