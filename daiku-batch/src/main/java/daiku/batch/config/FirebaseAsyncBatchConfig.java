package daiku.batch.config;

import daiku.batch.tasklet.BusinessDataAsyncToFirebaseTasklet;
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
public class FirebaseAsyncBatchConfig {

    private final BusinessDataAsyncToFirebaseTasklet tasklet;
    private final JobOperator jobOperator;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public FirebaseAsyncBatchConfig(
            BusinessDataAsyncToFirebaseTasklet tasklet,
            JobOperator jobOperator,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        this.tasklet = tasklet;
        this.jobOperator = jobOperator;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void testTask() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("businessDataAsyncFirebaseJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobOperator.run(firebaseAsyncJob(), params);
    }

    public Job firebaseAsyncJob() {
        return new JobBuilder("businessDataAsyncFirebaseJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
