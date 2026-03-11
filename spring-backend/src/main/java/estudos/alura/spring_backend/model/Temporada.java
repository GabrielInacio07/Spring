package estudos.alura.spring_backend.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Temporada(
        @JsonAlias("Season") Integer numero,
        @JsonAlias("Episodes") List<Episode> episode) {
}
