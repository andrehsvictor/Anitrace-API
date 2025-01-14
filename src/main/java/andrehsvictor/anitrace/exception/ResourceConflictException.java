package andrehsvictor.anitrace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {

    private static final long serialVersionUID = -2658201612973395242L;

    public ResourceConflictException(String message) {
        super(message);
    }

}
