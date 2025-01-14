package andrehsvictor.anitrace.token;

import java.time.Duration;
import java.time.Instant;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final StringRedisTemplate redisTemplate;

    private static final String REFRESH_TOKEN_PREFIX = "refresh_token:";
    private static final String REFRESH_TOKEN_VALUE = "";

    public void save(Jwt refreshToken) {
        Duration ttl = Duration
                .ofSeconds(refreshToken.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + refreshToken.getId(), REFRESH_TOKEN_VALUE, ttl);
    }

    public boolean existsById(String id) {
        return redisTemplate.hasKey(REFRESH_TOKEN_PREFIX + id);
    }

    public void deleteById(String id) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + id);
    }
}
