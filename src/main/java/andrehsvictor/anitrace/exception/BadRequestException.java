package andrehsvictor.anitrace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -8884946409642372853L;

    public BadRequestException(String message) {
        super(message);
    }

}
