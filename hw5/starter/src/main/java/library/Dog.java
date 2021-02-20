package library;

import org.springframework.beans.factory.InitializingBean;

public class Dog implements Animal, InitializingBean {

    public String name() {
        return "Dog";
    }

    public void afterPropertiesSet() throws Exception {
        System.out.printf("Hi, I am %s, woof-woof \n", name());
    }
}
