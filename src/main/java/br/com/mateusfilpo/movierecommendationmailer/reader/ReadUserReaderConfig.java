package br.com.mateusfilpo.movierecommendationmailer.reader;

import br.com.mateusfilpo.movierecommendationmailer.client.Client;
import br.com.mateusfilpo.movierecommendationmailer.dto.MovieWithValueGenreDTO;
import br.com.mateusfilpo.movierecommendationmailer.dto.UserDTO;
import br.com.mateusfilpo.movierecommendationmailer.model.Movie;
import br.com.mateusfilpo.movierecommendationmailer.model.User;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReadUserReaderConfig implements ItemReader<User> {
    private final Client client;
    private int userIndex = 0;
    private List<User> users = new ArrayList<>();

    public ReadUserReaderConfig(Client client) {
        this.client = client;
    }

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (users.isEmpty()) {
            fetchAllUsers();
        }

        if (userIndex < users.size()) {
            return users.get(userIndex++);
        } else {
            return null;
        }
    }

    private void fetchAllUsers() {
        List<UserDTO> response = client.getUsers();

        response.forEach(userDTO -> {
            List<MovieWithValueGenreDTO> movieList = client.getRecommendedMoviesForUser(userDTO.getId()).getContent();
            User user = new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());

            movieList.forEach(movieDTO -> {
                user.getMovies().add(new Movie(movieDTO.getTitle(), movieDTO.getDescription()));
            });

            users.add(user);
        });
    }
}
