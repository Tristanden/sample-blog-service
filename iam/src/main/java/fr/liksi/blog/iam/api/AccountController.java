package fr.liksi.blog.iam.api;


import fr.liksi.blog.iam.api.bean.ApiAccount;
import fr.liksi.blog.iam.domain.Account;
import fr.liksi.blog.iam.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/account")
@Api(value = "Account controller", produces = "application/json", consumes = "application/json", tags = "Account")
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "Create an account")
    public ResponseEntity<ApiAccount> createAccount(
            @ApiParam(name = "account", required = true) @Valid @RequestBody ApiAccount apiAccount) {
        LOG.info("Create account with email {}", apiAccount.getEmail());
        Account account = new Account();
        account.setEmail(apiAccount.getEmail());
        account.setFirstname(apiAccount.getFirstname());
        account.setLastname(apiAccount.getLastname());
        accountService.createAccount(account);
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(getClass()).getAccount(apiAccount.getEmail()));
        return ResponseEntity.created(controllerLinkBuilder.toUri()).body(apiAccount);
    }


    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "Get an account")
    public ResponseEntity<ApiAccount> getAccount(
            @ApiParam(name = "email", required = true) @PathVariable String email) {
        LOG.info("Get account for email {}", email);
        Account account = accountService.getAccount(email);
        ApiAccount apiAccount = new ApiAccount();
        apiAccount.setEmail(account.getEmail());
        apiAccount.setFirstname(account.getFirstname());
        apiAccount.setLastname(account.getLastname());
        return ResponseEntity.ok(apiAccount);
    }

}
