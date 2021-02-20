package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibAutoConfig {

    @Bean
    Animal cat() {
        return new Cat();
    }

    // This bean will be created only if "animal" property exists
    // in the application.properties and has value "dog".
    @Bean
    @ConditionalOnProperty(value = "animal", havingValue = "dog")
    Animal dog() {
        return new Dog();
    }
}
