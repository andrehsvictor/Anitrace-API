package andrehsvictor.anitrace.jwt.validation;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import andrehsvictor.anitrace.tokenrevocation.TokenRevocationService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RevokedJwtValidator implements OAuth2TokenValidator<Jwt> {

    private final TokenRevocationService revokedTokenService;

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (revokedTokenService.isRevoked(token)) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, "Token has been revoked", null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

}
