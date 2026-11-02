package daiku.batch.service;

import com.google.firebase.auth.FirebaseAuthException;
import daiku.domain.enums.DelFlg;
import daiku.domain.repository.AccountRepository;
import daiku.domain.repository.FirebaseRepository;
import daiku.domain.repository.GoalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FirebaseDisableAccountService {
    @Autowired
    private FirebaseRepository firebaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GoalRepository goalRepository;

    public void accountDisable() {
        accountRepository.selectOfDel().forEach(item -> {
            try {
                firebaseRepository.deleteAccount(item.getUid());
                item.setDelFlg(DelFlg.FIREBASE_DELETED);
                accountRepository.save(item);
            } catch (FirebaseAuthException e) {
                log.error("Firebase Account Delete Error");
            }
        });

    }
}
