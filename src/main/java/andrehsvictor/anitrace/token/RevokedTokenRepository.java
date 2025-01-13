package andrehsvictor.anitrace.token;

import java.time.Duration;
import java.time.Instant;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevokedTokenRepository {

    private final RedisTemplate<String, Integer> redisTemplate;

    private static final String REVOKED_TOKEN_PREFIX = "revoked_token:";
    private static final Integer REVOKED_TOKEN_VALUE = 0;

    public void save(Jwt revokedToken) {
        Duration ttl = Duration
                .ofSeconds(Instant.now().getEpochSecond() - revokedToken.getExpiresAt().getEpochSecond());
        redisTemplate.opsForValue().set(revokedToken.getId(), REVOKED_TOKEN_VALUE, ttl);
    }

    public boolean existsById(String id) {
        return redisTemplate.hasKey(REVOKED_TOKEN_PREFIX + id);
    }

    public void deleteById(String id) {
        redisTemplate.delete(REVOKED_TOKEN_PREFIX + id);
    }

}
