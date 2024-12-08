package br.com.mateusfilpo.movierecommendationmailer.client;

import br.com.mateusfilpo.movierecommendationmailer.dto.LoginRequestDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.MovieWithValueGenreDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.TokenResponseDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Client {

    Page<MovieWithValueGenreDTO> getRecommendedMoviesForUser(Long id);

    TokenResponseDTO authenticateUser(LoginRequestDTO loginRequest);

    List<UserDTO> getUsers();
}
