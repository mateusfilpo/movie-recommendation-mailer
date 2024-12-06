package br.com.mateusfilpo.movierecommendationmailer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = "pageable")
public class MovieWithValueGenreDTOPageImpl extends PageImpl<br.com.mateusfilpo.movierecommendationmailer.model.MovieWithValueGenreDTO> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MovieWithValueGenreDTOPageImpl(
            @JsonProperty("content") List<MovieWithValueGenreDTO> content,
            @JsonProperty("number") int ignoredPage,
            @JsonProperty("size") int ignoredSize,
            @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(0, 5), total);
    }

    public MovieWithValueGenreDTOPageImpl(List<MovieWithValueGenreDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public MovieWithValueGenreDTOPageImpl(List<MovieWithValueGenreDTO> content) {
        super(content);
    }
}
