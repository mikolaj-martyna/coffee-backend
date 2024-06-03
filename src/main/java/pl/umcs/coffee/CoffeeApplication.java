package pl.umcs.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CoffeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoffeeApplication.class, args);
  }
}
