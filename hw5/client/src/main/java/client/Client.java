package client;

import library.Cat;
import library.Dog;
import library.LibAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Client {

    @Bean
    @ConditionalOnBean(Cat.class)
    CatFriend catFriend() {
        return new CatFriend();
    }

    @Bean
    @ConditionalOnMissingBean(Cat.class)
    CatHater catHater() {
        return new CatHater();
    }

    @Bean
    @ConditionalOnBean(Dog.class)
    DogFriend dogFriend() {
        return new DogFriend();
    }

    @Bean
    @ConditionalOnMissingBean(Dog.class)
    DogHater dogHater() {
        return new DogHater();
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Client.class, args);

        System.out.println("Application started");
    }
}
