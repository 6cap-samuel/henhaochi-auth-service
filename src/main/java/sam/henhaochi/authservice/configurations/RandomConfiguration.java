package sam.henhaochi.authservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RandomConfiguration {

    @Bean
    public Random getRandom(){
        return new Random();
    }
}
