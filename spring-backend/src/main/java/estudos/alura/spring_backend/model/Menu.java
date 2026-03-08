package estudos.alura.spring_backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import estudos.alura.spring_backend.service.ConsumerAPI;
import estudos.alura.spring_backend.service.ConvertDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    private ConsumerAPI consumerapi = new ConsumerAPI();
    private ConvertDados converterDados = new ConvertDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d7b5168c";

    public void exibirMenu() throws JsonProcessingException {
        System.out.print("Digite o nome da serie: ");

        String nome = input.nextLine();;
        var json = consumerapi.obterDados(ENDERECO + nome.trim().replace(" " , "+") + API_KEY);

        Serie serie = converterDados.obterDados(json,Serie.class);
        System.out.println(serie);

        List<Temporada> listTemporada = new ArrayList<>();

        for (int i = 1; i <= serie.totalTemporadas(); i++) {
            json = consumerapi.obterDados(ENDERECO + nome.replace(" " , "+") + "&Season=" + i + API_KEY);
            Temporada temp = converterDados.obterDados(json, Temporada.class);
            listTemporada.add(temp);

        }

//        listTemporada.forEach(elemento -> elemento.episode()
//                .forEach(e -> System.out.println(e.titulo())));

        listTemporada.forEach(System.out::println);
    }
}
