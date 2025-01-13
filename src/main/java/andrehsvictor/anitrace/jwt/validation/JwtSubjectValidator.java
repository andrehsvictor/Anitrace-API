package andrehsvictor.anitrace.jwt.validation;

import java.util.UUID;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtSubjectValidator implements OAuth2TokenValidator<Jwt> {

    private final UserService userService;

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (!userService.existsById(UUID.fromString(token.getSubject()))) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, "Invalid subject", null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

}
