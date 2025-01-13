package andrehsvictor.anitrace.jwt.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtClaimsValidator implements OAuth2TokenValidator<Jwt> {

    private static final List<String> requiredClaims = List.of(
            "sub",
            "aud",
            "iss",
            "exp",
            "iat",
            "jti",
            "type");

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        List<String> missingClaims = new ArrayList<>();
        for (String claim : requiredClaims) {
            if (!token.getClaims().containsKey(claim)) {
                missingClaims.add(claim);
            }
        }
        if (!missingClaims.isEmpty()) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN,
                    "Missing required claims: " + missingClaims, null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

}
