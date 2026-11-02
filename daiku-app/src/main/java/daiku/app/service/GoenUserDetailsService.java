package daiku.app.service;

import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.DelFlg;
import daiku.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class GoenUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("User Details Service: Load user");

        if(Objects.isNull(username)) {
            log.error("username is empty");
            throw new UsernameNotFoundException("username is empty");
        }
        TAccounts account = accountRepository.selectByUid(username)
                .orElseThrow(() -> new UsernameNotFoundException("user info is empty"));

        if(DelFlg.FIREBASE_DELETED.equals(account.getDelFlg())) {
            log.error("アカウント削除情報はFirebaseに連携済み");
            throw new UsernameNotFoundException("already firebase account delete");
        }

        return new GoenUserDetails(
                username,
                account,
                AuthorityUtils.createAuthorityList("GOEN_USER")
        );
    }
}
