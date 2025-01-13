package andrehsvictor.anitrace.jwt.validation;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtTypeValidator implements OAuth2TokenValidator<Jwt> {

    private static final String TYPE_CLAIM = "type";

    private static final List<String> allowedTypes = List.of("access", "refresh");

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (!allowedTypes.contains(token.getClaimAsString(TYPE_CLAIM))) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, "Invalid token type", null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

}
