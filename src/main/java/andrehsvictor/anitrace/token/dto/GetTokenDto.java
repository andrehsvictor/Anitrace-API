package andrehsvictor.anitrace.token.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTokenDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
}
