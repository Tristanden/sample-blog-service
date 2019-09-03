package fr.liksi.blog.iam.service;

import fr.liksi.blog.iam.domain.Account;
import fr.liksi.blog.iam.exception.ObjectNotFound;
import fr.liksi.blog.iam.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        this.accountRepository.addAccount(account);
        return account;
    }

    public Account getAccount(String email) {
        return this.accountRepository.getAccount(email)
                .orElseThrow(() -> new ObjectNotFound("account " + email + " does not exist"));
    }

}
