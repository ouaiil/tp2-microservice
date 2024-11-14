package org.example.bankaccountservice.web;

import org.example.bankaccountservice.dto.BankAccountRequestDTO;
import org.example.bankaccountservice.dto.BankAccountResponseDTO;
import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.mappers.AccountMapper;
import org.example.bankaccountservice.repositories.BankAccountRepository;
import org.example.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController

@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/BankAccounts")
    public List<BankAccount> bankAccounts()
    {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/BankAccount/{id}")
    public BankAccount bankAccounts(@PathVariable String id)
    {
        return bankAccountRepository.findById(id).
                orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/BankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO)
    {
       return accountService.addAccount(requestDTO);
    }
    @PutMapping("/BankAccounts/{id}")
    public  BankAccount update(@PathVariable String id , @RequestBody BankAccount bankAccount)
    {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCeratedAt()!=null)account.setCeratedAt(bankAccount.getCeratedAt());
        if(bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);

    }
    @DeleteMapping("/BankAccount/{id}")
    public void delte(@PathVariable String id)
    {
        bankAccountRepository.deleteById(id);
    }
}
