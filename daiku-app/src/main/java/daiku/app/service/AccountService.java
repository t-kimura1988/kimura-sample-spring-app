package daiku.app.service;

import com.google.firebase.auth.FirebaseAuthException;
import daiku.app.service.input.account.*;
import daiku.app.service.output.account.AccountUpdateServiceOutput;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.DelFlg;
import daiku.domain.repository.AccountRepository;
import daiku.domain.repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FirebaseRepository firebaseRepository;

    public TAccounts showService(String uid) throws GoenNotFoundException, GoenIntegrityException {
        var account = accountRepository.selectByUid(uid)
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("uid: ", uid);
                            return new GoenNotFoundException("goal detail info no found", param);
                        }
                );

        if(DelFlg.DELETED.equals(account.getDelFlg())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("account uid: ", uid);
            throw  new GoenIntegrityException("アカウントが削除済み", param, "E0001");
        }

        if(DelFlg.FIREBASE_DELETED.equals(account.getDelFlg())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("account uid: ", uid);
            throw  new GoenIntegrityException("アカウントが削除済み", param, "E0005");
        }

        return account;
    }

    public TAccounts baseCreate(AccountCreateServiceInput input) throws FirebaseAuthException, GoenNotFoundException {
        var account = accountRepository.selectByUid(input.getUid());
        if (account.isPresent()) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("uid: ", input.getUid());
            throw  new GoenNotFoundException("this uid is already existed t_account :", param);
        }
        TAccounts accounts = new TAccounts();
        accounts.setUid(input.getUid());
        accounts.setEmail(input.getEmail());
        accounts.setFamilyName(input.getFamilyName());
        accounts.setGivenName(input.getGivenName());
        accounts.setNickName(input.getNickName());
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        accountRepository.save(accounts);
        firebaseRepository.accountClaims(input.getUid());

        return accounts;
    }

    public AccountUpdateServiceOutput update(AccountUpdateServiceInput input) throws GoenNotFoundException {
        accountRepository.selectById(input.getAccountId()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("id: ", input.getAccountId().toString());
            return new GoenNotFoundException("account id is not found", param);

        });
        accountRepository.save(input.toRepo());
        return AccountUpdateServiceOutput.builder()
                .accounts(accountRepository.selectByUid(input.getUid()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("uid: ", input.getUid());
                            return new GoenNotFoundException("account info no found", param);
                        }
                )).build();
    }

    public TAccounts delete(AccountDeleteServiceInput input) {
        accountRepository.save(input.toRepo());
        return input.toRepo();
    }

    public TAccounts reUpdate(AccountReUpdateServiceInput input) throws FirebaseAuthException, GoenNotFoundException {
        accountRepository.save(input.toRepo());
        return accountRepository.selectByUid(input.getAccount().getUid())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("uid: ", input.getAccount().getUid());
                            return new GoenNotFoundException("account info no found", param);
                        }
                );
    }

    public TAccounts upload(AccountUploadServiceInput input) throws GoenNotFoundException {
        accountRepository.save(input.updateData());
        return accountRepository.selectByUid(input.getAccount().getUid())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("uid: ", input.getAccount().getUid());
                            return new GoenNotFoundException("account info no found", param);
                        }
                );
    }


}
