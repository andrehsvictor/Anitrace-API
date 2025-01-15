package andrehsvictor.anitrace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenOperationException extends RuntimeException {

    private static final long serialVersionUID = 4250410632785313484L;

    public ForbiddenOperationException(String message) {
        super(message);
    }

}
