package library;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "animal", havingValue = "dog")
public class Dog implements Animal, InitializingBean {

    public String name() {
        return "Dog";
    }

    public void afterPropertiesSet() throws Exception {
        System.out.printf("Hi, I am %s, woof-woof \n", name());
    }
}
