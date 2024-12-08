package br.com.mateusfilpo.movierecommendationmailer.step;

import br.com.mateusfilpo.movierecommendationmailer.model.User;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendMovieRecommendationEmailStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Value("${chunkSize}")
    private int chunkSize;

    public SendMovieRecommendationEmailStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step sendMovieRecommendationEmailStep(
            ItemReader<User> readUserReader,
            ItemProcessor<User, SimpleMailMessage> processEmailUserProcessor,
            ItemWriter<SimpleMailMessage> sendEmailUserWriter) {
        return new StepBuilder("sendMovieRecommendationEmailStep", jobRepository)
                .<User, SimpleMailMessage>chunk(chunkSize, transactionManager)
                .reader(readUserReader)
                .processor(processEmailUserProcessor)
                .writer(sendEmailUserWriter)
                .build();
    }
}
