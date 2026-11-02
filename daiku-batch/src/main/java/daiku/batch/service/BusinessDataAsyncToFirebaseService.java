package daiku.batch.service;

import daiku.domain.model.param.firestore.BusinessDataAsync;
import daiku.domain.repository.AccountRepository;
import daiku.domain.repository.FirebaseRepository;
import daiku.domain.repository.GoalArchiveRepository;
import daiku.domain.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BusinessDataAsyncToFirebaseService {
    @Autowired
    private FirebaseRepository firebaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GoalArchiveRepository goalArchiveRepository;

    public void asyncBusinessDataFirebase() {
        firebaseRepository.asyncBusinessData(BusinessDataAsync.builder()
                .accountCount(accountRepository.accountCount())
                .goalCount(goalRepository.selectGoalCount(LocalDate.now()))
                .goalArchiveCount(goalArchiveRepository.selectGoalArchiveCount(LocalDate.now())).build());

    }
}
