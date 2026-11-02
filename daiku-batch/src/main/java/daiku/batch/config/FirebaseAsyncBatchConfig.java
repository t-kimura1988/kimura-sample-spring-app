package daiku.batch.config;

import daiku.batch.tasklet.BusinessDataAsyncToFirebaseTasklet;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class FirebaseAsyncBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BusinessDataAsyncToFirebaseTasklet tasklet1;

    @Autowired
    private JobLauncher jobLauncher;
    @Scheduled(cron = "0 0 0 * * *")
    public void testTask() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("businessDataAsyncFirebaseJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution exe = jobLauncher.run(firebaseAsyncJob(), params);

    }

    public Job firebaseAsyncJob() {
        return jobBuilderFactory.get("businessDataAsyncFirebaseJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(tasklet1)
                .build();
    }
}
