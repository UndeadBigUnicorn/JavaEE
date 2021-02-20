package client;

import org.springframework.beans.factory.InitializingBean;

public class DogFriend implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello, I am a friend of a dog, I love dogs!");
    }
}
