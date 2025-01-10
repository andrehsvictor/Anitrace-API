package andrehsvictor.anitrace.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtEncoder jwtEncoder;

    @Value("${anitrace.security.token.jwt.access-token.lifespan:15m}")
    private Duration accessTokenLifespan = Duration.ofMinutes(15);

    @Value("${anitrace.security.token.jwt.refresh-token.lifespan:1d}")
    private Duration refreshTokenLifespan = Duration.ofDays(1);

    @Value("${anitrace.security.token.jwt.audience:anitrace}")
    private String audience = "anitrace";

    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";
    private static final String TYPE_CLAIM = "type";

    public Jwt issueAccessToken(String subject) {
        Instant now = Instant.now();
        Instant expiration = now.plus(accessTokenLifespan);
        String issuer = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        String jti = UUID.randomUUID().toString();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(expiration)
                .claim(TYPE_CLAIM, ACCESS_TOKEN_TYPE)
                .id(jti)
                .audience(List.of(audience))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    public Jwt issueRefreshToken(String subject) {
        Instant now = Instant.now();
        Instant expiration = now.plus(refreshTokenLifespan);
        String issuer = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        String jti = UUID.randomUUID().toString();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(expiration)
                .claim(TYPE_CLAIM, REFRESH_TOKEN_TYPE)
                .id(jti)
                .audience(List.of(audience))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

}
