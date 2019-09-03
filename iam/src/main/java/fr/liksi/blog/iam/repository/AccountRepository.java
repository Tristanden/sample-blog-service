package fr.liksi.blog.iam.repository;

import fr.liksi.blog.iam.domain.Account;
import fr.liksi.blog.iam.exception.ObjectAlreadyExist;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {

    private Map<String, Account> accountMap = new HashMap<>();

    public void addAccount(Account account) {
        if (accountMap.containsKey(account.getEmail())) {
            throw new ObjectAlreadyExist("Account " + account.getEmail() + " already exists");
        }
        accountMap.put(account.getEmail(), account);
    }

    public Optional<Account> getAccount(String email) {
        return Optional.ofNullable(accountMap.get(email));
    }


}
