package com.example.accountService.service.impl;

import com.example.accountService.exception.AccountException;
import com.example.accountService.exception.AccountNotFoundException;
import com.example.accountService.exception.AccountServiceException;
import com.example.accountService.model.Account;
import com.example.accountService.repository.AccountRepository;
import com.example.accountService.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account createAccount(Account account){
        Long accountId=account.getId();
        log.info("create Account for account id : {}",accountId);
        Account createdAccount = null;
        try {
            createdAccount = accountRepository.save(account);
            log.info("Account created successfully for account id: {}",
                    accountId);
        } catch (DataAccessException dae) {
            log.error("Database access error while creating account: {}", dae.getMessage());
            throw new AccountServiceException("Database access error", dae);
        } catch (Exception e){
            log.error("Unexpected error while creating account : {}", e.getMessage());
            throw new AccountServiceException("Unexpected error while creating account",e);
        }
        return createdAccount;
    }


    @Override
    public Account getAccount(Long accountId) {
        log.info("get Account for id: {}",accountId);
        Optional<Account>  OpAccount=null;
        Account account = new Account();
         try{
             OpAccount = accountRepository.findById(accountId);
             if (OpAccount.isPresent()) {
                 account = OpAccount.get();
             }else{
                 log.error("No account found for id: {}", accountId);
                 throw new AccountNotFoundException("No account found for id: " + accountId);
             }

         }catch (DataAccessException dae){
             log.error("Database access error while getting Account: {}", dae.getMessage());
             throw new AccountServiceException("Database access error", dae);
         }catch (Exception e){
             log.error("No transaction found :{}",e.getMessage());
             throw new AccountServiceException("No Account found",e);
         }
        return account;
    }

    @Override
    public Account updateBalance(Account account) {
     Long accountId =account.getId();
     log.info("updating account for account id :{}",accountId);
      Account updateAccount = new Account();
      try{
          Optional<Account> existingAccount = accountRepository.findById(accountId);
          if (existingAccount.isPresent()) {
              account.setBalance(account.getBalance());
              accountRepository.save(account);
              log.info("Account updated successfully for id: {}", accountId);

          }
          else {
              log.error("No Account found id : {}", accountId);
              throw new AccountNotFoundException("No account found for id: " + accountId);
          }

      }catch (DataAccessException dae){
        log.error("Database access error while updating transaction: {}",dae.getMessage());
        throw new AccountNotFoundException("Database access error while updating transaction: "+dae);
      }catch (Exception e){
          log.error("Unexpected error while updating Account : {}", e.getMessage());
          throw new AccountServiceException("Unexpected error " +
                  "while updating Account",e);
      }
        return updateAccount;
    }
}
