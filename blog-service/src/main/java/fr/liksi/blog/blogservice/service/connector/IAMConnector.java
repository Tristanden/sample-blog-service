package fr.liksi.blog.blogservice.service.connector;

import fr.liksi.blog.blogservice.domain.Account;
import fr.liksi.blog.connector.iam.AccountApi;
import fr.liksi.blog.connector.iam.bean.ApiAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IAMConnector {

    private AccountApi accountApi;

    @Autowired
    public IAMConnector(AccountApi accountApi) {
        this.accountApi = accountApi;
    }


    public Optional<Account> getAccount(final String email) {
        ApiAccount account = this.accountApi.getAccountUsingGET(email);
        Account result = null;
        if (account != null) {
            result = new Account();
            result.setEmail(account.getEmail());
            result.setFirstname(account.getFirstname());
            result.setLastname(account.getLastname());
        }
        return Optional.ofNullable(result);
    }

}
