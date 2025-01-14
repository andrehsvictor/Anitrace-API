package andrehsvictor.anitrace.jwt;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.exception.UnauthorizedException;
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
        try {
            return jwtDecoder.decode(token);
        } catch (JwtException e) {
            throw new UnauthorizedException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
