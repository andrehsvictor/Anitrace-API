package andrehsvictor.anitrace.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import andrehsvictor.anitrace.exception.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto<String>> handleAllExceptions(Exception ex) {
        log.error("An error occurred", ex);
        return ResponseEntity.internalServerError().body(ErrorDto.ofMessage("An error occurred"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorDto<String>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(401).body(ErrorDto.ofMessage(ex.getMessage()));
    }

}
