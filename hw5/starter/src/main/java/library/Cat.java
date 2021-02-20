package library;

import org.springframework.beans.factory.InitializingBean;

public class Cat implements Animal, InitializingBean {

    public String name() {
        return "Cat";
    }

    public void afterPropertiesSet() throws Exception {
        System.out.printf("Hi, I am %s, meow \n", name());
    }
}
