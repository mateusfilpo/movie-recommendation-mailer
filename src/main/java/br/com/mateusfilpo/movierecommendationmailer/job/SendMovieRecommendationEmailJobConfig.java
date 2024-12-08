package br.com.mateusfilpo.movierecommendationmailer.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendMovieRecommendationEmailJobConfig {

    private final JobRepository jobRepository;

    public SendMovieRecommendationEmailJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job sendMovieRecommendationEmailJob(Step sendMovieRecommendationEmailStep) {
        return new JobBuilder("sendMovieRecommendationEmailJob", jobRepository)
                .start(sendMovieRecommendationEmailStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
