package andrehsvictor.anitrace.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String displayName;
    private String bio;
    private String avatarUrl;
    private String createdAt;
}
