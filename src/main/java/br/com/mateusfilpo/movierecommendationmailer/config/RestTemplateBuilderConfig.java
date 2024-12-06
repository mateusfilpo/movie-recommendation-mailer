package br.com.mateusfilpo.movierecommendationmailer.config;

import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {

    String rootUrl = "http://localhost:8080";

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {

        return configurer.configure(new RestTemplateBuilder())
                .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl));
    }
}
