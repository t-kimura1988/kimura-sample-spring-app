package daiku.app.controller;

import com.google.firebase.auth.FirebaseAuthException;
import daiku.app.controller.input.account.AccountCreateParam;
import daiku.app.controller.input.account.AccountUploadParam;
import daiku.app.service.AccountService;
import daiku.app.service.input.account.AccountDeleteServiceInput;
import daiku.app.service.input.account.AccountReUpdateServiceInput;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TAccounts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public TAccounts show(@AuthenticationPrincipal GoenUserDetails userDetails) throws GoenNotFoundException, GoenIntegrityException {
        return accountService.showService(userDetails.account().getUid());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TAccounts create(
            @RequestBody @Validated AccountCreateParam param,
            @AuthenticationPrincipal GoenUserDetails userDetails) throws FirebaseAuthException, GoenNotFoundException {
        return accountService.baseCreate(param.toService(userDetails.account()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TAccounts update(
            @RequestBody @Validated AccountCreateParam param,
            @AuthenticationPrincipal GoenUserDetails userDetails) throws GoenNotFoundException {
        return accountService.update(param.toUpdateService(userDetails.account())).toResponse();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public TAccounts delete(
            @AuthenticationPrincipal GoenUserDetails userDetails) {
        return accountService.delete(AccountDeleteServiceInput.builder()
                .account(userDetails.account()).build());
    }

    @RequestMapping(value = "/re-update", method = RequestMethod.POST)
    public TAccounts reUpdate(
            @AuthenticationPrincipal GoenUserDetails userDetails) throws FirebaseAuthException, GoenNotFoundException {
        return accountService.reUpdate(AccountReUpdateServiceInput.builder()
                .account(userDetails.account()).build());
    }

    @PostMapping(value = "/upload" )
    public TAccounts upload(@RequestBody @Validated AccountUploadParam param, @AuthenticationPrincipal GoenUserDetails userDetails) throws GoenNotFoundException {
        return accountService.upload(param.toService(userDetails.account()));
    }

}
