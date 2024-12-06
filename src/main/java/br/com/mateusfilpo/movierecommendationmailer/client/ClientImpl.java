package br.com.mateusfilpo.movierecommendationmailer.client;

import br.com.mateusfilpo.movierecommendationmailer.model.MovieWithValueGenreDTO;
import br.com.mateusfilpo.movierecommendationmailer.model.MovieWithValueGenreDTOPageImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@Service
public class ClientImpl implements Client {

    private final RestTemplateBuilder restTemplateBuilder;

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
        headers.set("Authorization", "Bearer eyJraWQiOiI4OWM2YWY2OC05OTJkLTQwODktODYyYy0xODEzMjkwMmUwZjgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJteWNsaWVudGlkIiwiYXVkIjoibXljbGllbnRpZCIsIm5iZiI6MTczMzUxNTc1NSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzMzNjAyMTU1LCJpYXQiOjE3MzM1MTU3NTUsImp0aSI6ImEyNGJmZTUwLTM3OGUtNDhiYi1hZmRkLWQwZWMyOTg5Y2M4ZiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwidXNlcm5hbWUiOiJtYXRldXNmaWxwbyJ9.fQSz5v6NO0-FICVx_bZNfEPOJTB9-_s6ptg_5VtNNTQImZXmNmhBn0O2cOW7q_otjMnUKnc8TNsHxJFR81zIY95fwlI909nHsx1MQMIVTW0zo9dvLcVcybvWm4bkV6ZK1CQHm6aWmsvC1LJKhRXzWp8fZolOLvTmkCFhQZ0OQIA5VhMxLP3wIS6xGUMZcmYUYYLXuocHtx-ZOsCpbd6iikbIWNtW9N5eJR6x0dKf5AdylTKsyI1WL8YOfd9Bj0U5c8lNOFsMDFFpUxoPd_4__HgkjnvtlNSBvfofVVTaDlknrpwlgfxjIlresK0k0mniFOyi43gyiBlt8QTxWXSCTw");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieWithValueGenreDTOPageImpl> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                MovieWithValueGenreDTOPageImpl.class
        );

        return response.getBody();
    }


}
