package estudos.alura.spring_backend.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Episode(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer numero,
        @JsonAlias("Released") String lancamento,
        @JsonAlias("imdbRating") String avaliacao) {
}
