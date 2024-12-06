package br.com.mateusfilpo.movierecommendationmailer.bootstrap;

import br.com.mateusfilpo.movierecommendationmailer.client.Client;
import br.com.mateusfilpo.movierecommendationmailer.model.MovieWithValueGenreDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final Client client;

    public Bootstrap(Client client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        Page<MovieWithValueGenreDTO> result = client.getRecommendedMoviesForUser(1L);
        result.getContent().forEach(movie -> {
            System.out.println(movie.getTitle());
            movie.getGenres().forEach(genre -> System.out.println(genre.getName()));
            System.out.println("---------------------------------");
        });
    }
}
