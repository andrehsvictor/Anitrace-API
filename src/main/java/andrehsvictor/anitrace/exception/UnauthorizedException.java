package andrehsvictor.anitrace.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    private static final long serialVersionUID = -1034258835771123099L;

    public UnauthorizedException(String message) {
        super(message);
    }

}
