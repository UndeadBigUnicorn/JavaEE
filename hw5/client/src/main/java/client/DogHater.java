package client;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!dog")
@Component
public class DogHater implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello, I hate dogs!");
    }
}