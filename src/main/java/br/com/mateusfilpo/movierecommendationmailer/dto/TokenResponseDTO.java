package br.com.mateusfilpo.movierecommendationmailer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponseDTO {

    @JsonProperty("access_token")
    private String token;

    public TokenResponseDTO() {
    }

    public TokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
