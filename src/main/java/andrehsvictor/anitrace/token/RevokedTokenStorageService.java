package andrehsvictor.anitrace.token;

import java.time.Duration;
import java.time.Instant;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevokedTokenStorageService {

    private final RedisTemplate<String, Integer> redisTemplate;

    private static final String REVOKED_TOKEN_PREFIX = "revoked_token:";
    private static final Integer REVOKED_TOKEN_VALUE = 0;

    public void save(Jwt revokedToken) {
        Duration ttl = Duration
                .ofSeconds(Instant.now().getEpochSecond() - revokedToken.getExpiresAt().getEpochSecond());
        redisTemplate.opsForValue().set(revokedToken.getId(), REVOKED_TOKEN_VALUE, ttl);
    }

    public boolean exists(Jwt revokedToken) {
        return redisTemplate.hasKey(REVOKED_TOKEN_PREFIX + revokedToken.getId());
    }

    public void delete(Jwt revokedToken) {
        redisTemplate.delete(REVOKED_TOKEN_PREFIX + revokedToken.getId());
    }

}
