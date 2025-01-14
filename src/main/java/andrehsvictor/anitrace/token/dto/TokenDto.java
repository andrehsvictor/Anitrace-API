package andrehsvictor.anitrace.token.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    @NotBlank(message = "Token is required")
    @Pattern(message = "Token must be a valid JWT", regexp = "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]*$")
    private String token;
    
}
