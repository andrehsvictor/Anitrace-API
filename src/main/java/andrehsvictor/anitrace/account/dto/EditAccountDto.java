package andrehsvictor.anitrace.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditAccountDto {
    private String username;
    private String email;
    private String avatarUrl;
    private String bio;
}
