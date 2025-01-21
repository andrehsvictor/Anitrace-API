package andrehsvictor.anitrace.jikan.impl;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.cache.JikanCache;

@Component
@RequiredArgsConstructor
public class JikanRedisCache implements JikanCache {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, OffsetDateTime expires) {
        Duration duration = Duration.between(OffsetDateTime.now(), expires);
        redisTemplate.opsForValue().set(key, value.toString(), duration);
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

}
