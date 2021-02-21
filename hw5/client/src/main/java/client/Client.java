package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Client {

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);

        System.out.println("Application started");
    }
}
