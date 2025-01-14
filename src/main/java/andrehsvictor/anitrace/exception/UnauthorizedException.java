package andrehsvictor.anitrace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends AuthenticationException {

    private static final long serialVersionUID = -1034258835771123099L;

    public UnauthorizedException(String message) {
        super(message);
    }

}
