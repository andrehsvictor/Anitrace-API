package andrehsvictor.anitrace.revokedtoken;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevokedTokenService {

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
