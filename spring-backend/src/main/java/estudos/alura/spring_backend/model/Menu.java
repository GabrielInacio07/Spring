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

    private List<Serie> listaSeries = new ArrayList<>();

    public void exibirMenu() throws JsonProcessingException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                     1 - Adicionar séries
                     2 - Buscar episódios
                     3 - Listar séries buscadas
                     0 - Sair
                    """;

            System.out.println(menu);
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSerieBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        }
    }

    private void buscarSerieWeb() throws JsonProcessingException {
        Serie dados = getDadosSeries();
        listaSeries.add(dados);
    }

    private Serie getDadosSeries() throws JsonProcessingException {
        System.out.print("Digite o nome da série: ");
        String nome = input.nextLine();
        String json = consumerapi.obterDados(ENDERECO + nome.trim().replace(" ", "+") + API_KEY);
        return converterDados.obterDados(json, Serie.class);
    }

    private void buscarEpisodioPorSerie() throws JsonProcessingException {
        Serie dadosSerie = getDadosSeries();
        List<Temporada> temporadas = new ArrayList<>();

        for (int i = 0; i <= dadosSerie.totalTemporadas(); i++) {
            String json = consumerapi.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + API_KEY);
            Temporada dadosTemporada = converterDados.obterDados(json, Temporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);
    }

    private void listarSerieBuscadas() {
        listaSeries.forEach(System.out::println);
    }
}
