package estudos.alura.spring_backend;

import estudos.alura.spring_backend.model.Tarefa;
import estudos.alura.spring_backend.service.ConvertDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;

@SpringBootApplication
public class SpringBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        ConsumerAPI consumerapi = new ConsumerAPI();
//        var json = consumerapi.obterDados("https://www.omdbapi.com/?t=vikings&apikey=d7b5168c");
//
//        ConvertDados converter = new ConvertDados();
//
//        Serie dados = converter.obterDados(json,Serie.class);
//
//        System.out.println(dados);

        Tarefa tarefa = new Tarefa("Serializando OBJECT", true, "Gabriel");

        ConvertDados convert = new ConvertDados();

        String jsonTarefa = convert.converterToJson(tarefa);

        var objectTarefa = convert.converterToObject(jsonTarefa, Tarefa.class);

        FileWriter arquivo = new FileWriter("tarefa.json");
        arquivo.write(jsonTarefa);


        System.out.println("Serializado: " + jsonTarefa);
        System.out.println("Desserializado: " + objectTarefa);
        arquivo.close();


    }

}
