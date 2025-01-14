package andrehsvictor.anitrace.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import andrehsvictor.anitrace.exception.dto.ErrorsDto;
import andrehsvictor.anitrace.exception.dto.FieldErrorDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorsDto<String>> handleAllExceptions(Exception ex) {
        log.error("An error occurred", ex);
        return ResponseEntity.internalServerError().body(ErrorsDto.ofMessage("An error occurred"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorsDto<String>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(401).body(ErrorsDto.ofMessage(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorsDto<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorsDto<String> errorsDto = ErrorsDto.ofMessage(ex.getMessage());
        return ResponseEntity.status(404).body(errorsDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorsDto<FieldErrorDto> errorsDto = new ErrorsDto<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            FieldErrorDto fieldErrorDto = new FieldErrorDto();
            fieldErrorDto.setField(e.getField());
            fieldErrorDto.setMessage(e.getDefaultMessage());
            errorsDto.getErrors().add(fieldErrorDto);
        });
        return ResponseEntity.badRequest().body(errorsDto);
    }

}
