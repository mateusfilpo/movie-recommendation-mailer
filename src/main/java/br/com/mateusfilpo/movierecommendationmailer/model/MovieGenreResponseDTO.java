package br.com.mateusfilpo.movierecommendationmailer.model;

public class MovieGenreResponseDTO {

    private String name;

    public MovieGenreResponseDTO() {
    }

    public MovieGenreResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
