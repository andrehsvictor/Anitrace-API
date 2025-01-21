package andrehsvictor.anitrace.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendEmailDto {
    private String to;
    private String subject;
    private String text;
}
