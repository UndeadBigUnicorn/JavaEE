package client;

import org.springframework.beans.factory.InitializingBean;

public class CatFriend implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello, I am a friend of a cat, I love cats!");
    }
}
