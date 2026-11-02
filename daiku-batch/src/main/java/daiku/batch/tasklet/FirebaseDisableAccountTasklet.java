package daiku.batch.tasklet;

import daiku.batch.service.FirebaseDisableAccountService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirebaseDisableAccountTasklet implements Tasklet {

    @Autowired
    FirebaseDisableAccountService firebaseDisableAccountService;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        firebaseDisableAccountService.accountDisable();
        return RepeatStatus.FINISHED;
    }
}
