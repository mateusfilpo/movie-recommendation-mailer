package br.com.mateusfilpo.movierecommendationmailer.bootstrap;

import br.com.mateusfilpo.movierecommendationmailer.client.Client;
import br.com.mateusfilpo.movierecommendationmailer.dto.LoginRequestDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.MovieWithValueGenreDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("test")
@Component
public class BootstrapData implements CommandLineRunner {

    private final Client client;

    @Value("${login.username}")
    private String username;

    @Value("${login.password}")
    private String password;

    public BootstrapData(Client client) {
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

        String token = client.authenticateUser(new LoginRequestDTO(username, password)).getToken();
        System.out.println(token);

        System.out.println("--------------------------------------");

        List<UserDTO> userDTOList = client.getUsers();
        userDTOList.forEach(userDTO -> {
            System.out.println(userDTO.getId());
            System.out.println(userDTO.getFirstName());
            System.out.println(userDTO.getLastName());
            System.out.println(userDTO.getEmail());
            System.out.println("--------------------------------------");
        });
    }
}
