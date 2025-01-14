package andrehsvictor.anitrace.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private String id;
    private String username;
    private String email;
    private boolean emailVerified;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private String createdAt;
    private String updatedAt;
}
