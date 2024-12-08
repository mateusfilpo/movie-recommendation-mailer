package br.com.mateusfilpo.movierecommendationmailer.client;

import br.com.mateusfilpo.movierecommendationmailer.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Base64;
import java.util.List;

@Service
public class ClientImpl implements Client {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${login.username}")
    private String username;

    @Value("${login.password}")
    private String password;

    @Value("${security.client-id}")
    private String clientId;

    @Value("${security.client-secret}")
    private String clientSecret;

    public static final String RECOMMENDED_MOVIE_PATH = "/users/{id}/recommended-movies?pageNumber=0&pageSize=5";

    public ClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Page<MovieWithValueGenreDTO> getRecommendedMoviesForUser(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        String uri = UriComponentsBuilder
                .fromPath(RECOMMENDED_MOVIE_PATH)
                .buildAndExpand(id)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticateUser(new LoginRequestDTO(username, password)).getToken());

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieWithValueGenreDTOPageImpl> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                MovieWithValueGenreDTOPageImpl.class
        );

        return response.getBody();
    }

    @Override
    public TokenResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        RestTemplate restTemplate = restTemplateBuilder.rootUri("http://localhost:9000").build();

        String uri = "/oauth2/token";

        String credentials = String.format("%s:%s", clientId, clientSecret);
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + base64Credentials);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", loginRequest.getUsername());
        formData.add("password", loginRequest.getPassword());
        formData.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                TokenResponseDTO.class
        );

        return response.getBody();
    }

    @Override
    public List<UserDTO> getUsers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        String uri = "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticateUser(new LoginRequestDTO(username, password)).getToken());


        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        );

        return response.getBody();
    }
}
