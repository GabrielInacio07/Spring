package estudos.alura.spring_backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import estudos.alura.spring_backend.service.ConsumerAPI;
import estudos.alura.spring_backend.service.ConvertDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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



        List<Episode> listaEpisodios = listTemporada
                .stream()
                .flatMap(temp -> temp.episode().stream()).toList();

        //Filtrando por avaliação
        System.out.println("Top 5 ep:");
        listaEpisodios.stream()
                .filter(ep ->  ep.avaliacao() != null && !ep.avaliacao().isEmpty() && !ep.avaliacao().equals("N/A"))
                .sorted(Comparator.comparing(Episode::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);


              //Filtrando por ano de lançamento
        List<Episodio_item> dadosEpisodio = listTemporada.stream()
                .flatMap(temporada -> temporada.episode().stream()
                        .map(episode -> new Episodio_item(temporada.numero(), episode)))
                .toList();

        dadosEpisodio.forEach(System.out::println);

        System.out.println("Digite o episódio desejado: ");
        String trechoTitulo = input.nextLine();
        Optional<Episodio_item> episodioBuscado = dadosEpisodio.stream()
                .filter(episodioItem -> episodioItem.getTitulo()
                        .toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + episodioBuscado.get());
        }else{
            System.out.println("Episódio não encontrado");
        }

        Map<Integer, Double> avaliacaoTemporada = dadosEpisodio
                .stream()
                .filter(episodioItem -> episodioItem.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio_item::getTemporada,
                        Collectors.averagingDouble(Episodio_item::getAvaliacao)));
        System.out.println(avaliacaoTemporada);

        DoubleSummaryStatistics estatics = dadosEpisodio
                .stream()
                .filter(episodioItem -> episodioItem.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio_item::getAvaliacao));

        System.out.println("Média: " + estatics.getAverage());
        System.out.println("Melhor episódio: " + estatics.getMax());
        System.out.println("Pior episódio: " + estatics.getMin());
        System.out.println("Quantia de de episódio: " + estatics.getCount());

//
//        System.out.println("Digite a filtragem por an, dos episódios: ");
//        int ano = Integer.parseInt(input.nextLine());
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//
//        dadosEpisodio
//                .stream()
//                .filter(episodioItem -> episodioItem.getDataLancamento() != null
//                        && episodioItem.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episodio: " + e.getTitulo() +
//                                " Data Lançamento: " + e.getDataLancamento().format(formatter)
//                ));
    }
}
