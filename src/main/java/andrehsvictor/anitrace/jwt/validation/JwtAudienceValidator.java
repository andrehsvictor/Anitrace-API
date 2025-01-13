package andrehsvictor.anitrace.jwt.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAudienceValidator implements OAuth2TokenValidator<Jwt> {

    @Value("${anitrace.security.jwt.audience:anitrace}")
    private List<String> audiences = List.of("anitrace");

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (!audiences.contains(token.getAudience())) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN, "Invalid audience", null);
            return OAuth2TokenValidatorResult.failure(error);
        }
        return OAuth2TokenValidatorResult.success();
    }

}
