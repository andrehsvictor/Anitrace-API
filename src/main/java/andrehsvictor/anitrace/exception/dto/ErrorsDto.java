package andrehsvictor.anitrace.exception.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsDto<T> {
    private List<T> errors = new ArrayList<>();

    public static <T> ErrorsDto<T> ofOne(T error) {
        ErrorsDto<T> errorDto = new ErrorsDto<>();
        errorDto.getErrors().add(error);
        return errorDto;
    }

    public static ErrorsDto<String> ofMessage(String message) {
        ErrorsDto<String> errorDto = new ErrorsDto<>();
        errorDto.getErrors().add(message);
        return errorDto;
    }
}
