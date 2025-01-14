package andrehsvictor.anitrace.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDto {
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String avatarUrl;
    private String bio;
}
