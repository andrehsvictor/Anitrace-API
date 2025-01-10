package andrehsvictor.anitrace.jwt;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtIssuer jwtIssuer;
    private final JwtDecoder jwtDecoder;

    public Jwt issueAccessToken(String subject) {
        return jwtIssuer.issueAccessToken(subject);
    }

    public Jwt issueRefreshToken(String subject) {
        return jwtIssuer.issueRefreshToken(subject);
    }

    public Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }
}
