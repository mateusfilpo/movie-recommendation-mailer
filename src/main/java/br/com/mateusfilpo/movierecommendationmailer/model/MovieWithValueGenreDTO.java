package br.com.mateusfilpo.movierecommendationmailer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class MovieWithValueGenreDTO {

    private String title;
    private String description;
    private List<MovieGenreResponseDTO> genres = new ArrayList<>();

    @JsonIgnore
    private Double distance;

    public MovieWithValueGenreDTO() {
    }

    public MovieWithValueGenreDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<MovieGenreResponseDTO> getGenres() {
        return genres;
    }

    public Double getDistance() {
        return distance;
    }
}
