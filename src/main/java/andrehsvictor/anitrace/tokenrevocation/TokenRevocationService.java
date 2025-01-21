package andrehsvictor.anitrace.tokenrevocation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "anitrace.security.token.jwt.revocation.enabled", havingValue = "true")
public class TokenRevocationService {

    private final RevokedTokenRepository revokedTokenRepository;

    public void revoke(Jwt token) {
        revokedTokenRepository.save(token);
    }

    public boolean isRevoked(Jwt token) {
        return revokedTokenRepository.existsById(token.getId());
    }

    public void unrevoke(Jwt token) {
        revokedTokenRepository.deleteById(token.getId());
    }
}
