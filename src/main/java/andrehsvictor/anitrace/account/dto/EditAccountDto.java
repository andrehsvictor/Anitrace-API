package andrehsvictor.anitrace.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditAccountDto {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(message = "Username must contain only letters, numbers, underscores and hyphens", regexp = "^[A-Za-z0-9_-]+$")
    private String username;

    @Size(min = 3, max = 20, message = "Display name must be between 3 and 20 characters")
    private String displayName;

    @Email(message = "Email must be a valid email address")
    private String email;

    @Size(max = 100, message = "Avatar URL must be at most 100 characters")
    @Pattern(message = "Avatar URL must be a valid URL", regexp = "^(http|https)://.*$")
    private String avatarUrl;

    @Size(max = 255, message = "Bio must be at most 255 characters")
    private String bio;

}
