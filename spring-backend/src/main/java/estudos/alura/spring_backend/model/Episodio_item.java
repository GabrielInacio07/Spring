package estudos.alura.spring_backend.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio_item {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private String lancamento;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio_item(Integer numeroTemp, Episode episode) {

        this.temporada = numeroTemp;
        this.titulo = episode.titulo();
        this.numeroEpisodio = episode.numero();
        try {
            this.avaliacao = Double.valueOf(episode.avaliacao());
        }catch (NumberFormatException error){
            setAvaliacao(0.0);
        }

        try{
            this.dataLancamento = LocalDate.parse(episode.lancamento());

        }catch (DateTimeException error){
            setDataLancamento(null);
        }
    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Episodio_item{" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", lancamento='" + lancamento + '\'' +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}
