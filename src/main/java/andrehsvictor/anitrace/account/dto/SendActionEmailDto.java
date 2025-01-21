package andrehsvictor.anitrace.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendActionEmailDto {

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Url is required")
    @Pattern(message = "Invalid url", regexp = "^https?://.*$")
    private String url;

    @NotEmpty(message = "Action is required")
    @Pattern(regexp = "^(VERIFY_EMAIL|RESET_PASSWORD)$", message = "Invalid action")
    private String action;

}
