package andrehsvictor.anitrace.jikan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.cache.JikanCache;

@Configuration
@RequiredArgsConstructor
public class JikanConfig {

    private final JikanCache jikanCache;

    @Bean
    Jikan jikan() {
        return new Jikan.JikanBuilder()
                .cache(jikanCache)
                .build();
    }

}
