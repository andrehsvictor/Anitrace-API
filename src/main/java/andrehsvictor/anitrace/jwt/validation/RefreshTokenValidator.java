package andrehsvictor.anitrace.jwt.validation;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import andrehsvictor.anitrace.token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RefreshTokenValidator implements OAuth2TokenValidator<Jwt> {

    private final RefreshTokenRepository refreshTokenRepository;

    private final static String REFRESH_TOKEN_TYPE = "refresh";

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        OAuth2Error error;
        if (isRefreshTokenType(token) && !isTokenValid(token)) {
            error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, "Refresh token expired", null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

    private boolean isTokenValid(Jwt token) {
        return refreshTokenRepository.existsById(token.getId());
    }

    private boolean isRefreshTokenType(Jwt token) {
        return token.getClaim("type").equals(REFRESH_TOKEN_TYPE);
    }

}
