To set an active profile use `client/pom.xml`:
```xml
...
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <profiles>
            <profile>dog</profile>
        </profiles>
    </configuration>
</plugin>
...
```