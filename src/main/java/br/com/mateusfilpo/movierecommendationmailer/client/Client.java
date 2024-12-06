package br.com.mateusfilpo.movierecommendationmailer.client;

import br.com.mateusfilpo.movierecommendationmailer.model.MovieWithValueGenreDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Client {

    Page<MovieWithValueGenreDTO> getRecommendedMoviesForUser(Long id);
}
