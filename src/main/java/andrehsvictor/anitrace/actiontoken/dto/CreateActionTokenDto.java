package andrehsvictor.anitrace.actiontoken.dto;

import java.util.UUID;

import andrehsvictor.anitrace.actiontoken.ActionTokenType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActionTokenDto {
    private ActionTokenType type;
    private UUID userId;
}
