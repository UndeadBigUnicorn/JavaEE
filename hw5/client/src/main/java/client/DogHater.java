package client;

import org.springframework.beans.factory.InitializingBean;

public class DogHater implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello, I hate dogs!");
    }
}