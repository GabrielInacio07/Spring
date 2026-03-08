package estudos.alura.spring_backend;

import estudos.alura.spring_backend.model.Episode;
import estudos.alura.spring_backend.model.Serie;
import estudos.alura.spring_backend.model.Tarefa;
import estudos.alura.spring_backend.model.Temporada;
import estudos.alura.spring_backend.service.ConsumerAPI;
import estudos.alura.spring_backend.service.ConvertDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ConsumerAPI consumerapi = new ConsumerAPI();
        var json = consumerapi.obterDados("https://www.omdbapi.com/?t=vikings&apikey=d7b5168c");

        ConvertDados converter = new ConvertDados();

        Serie serie = converter.obterDados(json,Serie.class);
        //System.out.println(serie);

        json = consumerapi.obterDados("https://www.omdbapi.com/?t=vikings&Season=2&episode=6&apikey=d7b5168c");
        Episode episode = converter.obterDados(json,Episode.class);
        //System.out.println(episode);

        List<Temporada> listTemporada = new ArrayList<>();

        for (int i = 1; i <= serie.totalTemporadas(); i++) {
            json = consumerapi.obterDados("https://www.omdbapi.com/?t=vikings&Season=" + i + "&apikey=d7b5168c");
            Temporada temp = converter.obterDados(json, Temporada.class);
            listTemporada.add(temp);

        }

        listTemporada.forEach(System.out::println);
    }

}
