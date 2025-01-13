package andrehsvictor.anitrace.exception.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto<T> {
    private List<T> errors;

    public static <T> ErrorDto<T> ofOne(T error) {
        ErrorDto<T> errorDto = new ErrorDto<>();
        errorDto.getErrors().add(error);
        return errorDto;
    }

    public static ErrorDto<String> ofMessage(String message) {
        ErrorDto<String> errorDto = new ErrorDto<>();
        errorDto.getErrors().add(message);
        return errorDto;
    }
}
