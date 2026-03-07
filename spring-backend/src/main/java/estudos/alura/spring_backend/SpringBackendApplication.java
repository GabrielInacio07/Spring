package estudos.alura.spring_backend;

import estudos.alura.spring_backend.service.ConsumerAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        ConsumerAPI consumerapi = new ConsumerAPI();

        var json = consumerapi.obterDados("https://www.omdbapi.com/?t=Sinners&apikey=d7b5168c");
        System.out.println(json);

        json = consumerapi.obterDados("https://viacep.com.br/ws/SP/Rosana/Rua+Encantado/json");
        System.out.println(json);


    }

}
