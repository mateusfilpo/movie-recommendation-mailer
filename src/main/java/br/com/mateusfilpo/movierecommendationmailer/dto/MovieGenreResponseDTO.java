package br.com.mateusfilpo.movierecommendationmailer.dto;

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
