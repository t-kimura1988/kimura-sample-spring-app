package daiku.batch.config;

import daiku.batch.tasklet.BusinessDataAsyncToFirebaseTasklet;
import daiku.batch.tasklet.FirebaseDisableAccountTasklet;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class FirebaseDisableAccountConfig {

    private final FirebaseDisableAccountTasklet firebaseAccountDeleteTasklet;
    private final BusinessDataAsyncToFirebaseTasklet businessDataAsyncToFirebaseTasklet;
    private final JobOperator jobOperator;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public FirebaseDisableAccountConfig(
            FirebaseDisableAccountTasklet firebaseAccountDeleteTasklet,
            BusinessDataAsyncToFirebaseTasklet businessDataAsyncToFirebaseTasklet,
            JobOperator jobOperator,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        this.firebaseAccountDeleteTasklet = firebaseAccountDeleteTasklet;
        this.businessDataAsyncToFirebaseTasklet = businessDataAsyncToFirebaseTasklet;
        this.jobOperator = jobOperator;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void testTask() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("firebaseDisableAccountJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobOperator.run(firebaseDisableJob(), params);
    }

    public Job firebaseDisableJob() {
        return new JobBuilder("firebaseDisableAccountJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(firebaseAccountDeleteTasklet, transactionManager)
                .build();
    }
}
