package andrehsvictor.anitrace.jikan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sandrohc.jikan.Jikan;

@Configuration
public class JikanConfig {

    @Bean
    Jikan jikan() {
        return new Jikan();
    }

}
