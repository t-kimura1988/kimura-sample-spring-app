package daiku.batch.config;

import daiku.batch.tasklet.BusinessDataAsyncToFirebaseTasklet;
import daiku.batch.tasklet.FirebaseDisableAccountTasklet;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class FirebaseDisableAccountConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FirebaseDisableAccountTasklet firebaseAccountDeleteTasklet;

    @Autowired
    private BusinessDataAsyncToFirebaseTasklet businessDataAsyncToFirebaseTasklet;

    @Autowired
    private JobLauncher jobLauncher;
    @Scheduled(cron = "0 0 0 * * *")
    public void testTask() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("firebaseDisableAccountJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution exe = jobLauncher.run(firebaseDisableJob(), params);

    }

    public Job firebaseDisableJob() {
        return jobBuilderFactory.get("firebaseDisableAccountJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(firebaseAccountDeleteTasklet)
                .build();
    }
}
