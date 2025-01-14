package andrehsvictor.anitrace.exception.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorDto {
    private String field;
    private String message;
}
