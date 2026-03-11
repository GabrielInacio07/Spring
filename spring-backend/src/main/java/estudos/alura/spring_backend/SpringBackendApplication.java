package estudos.alura.spring_backend;

import estudos.alura.spring_backend.model.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class SpringBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Menu menu = new Menu();
        menu.exibirMenu();



    }

}
