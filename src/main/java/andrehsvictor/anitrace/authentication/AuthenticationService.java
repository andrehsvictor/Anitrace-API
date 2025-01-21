package andrehsvictor.anitrace.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.authentication.dto.CredentialsDto;
import andrehsvictor.anitrace.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public Authentication authenticate(CredentialsDto credentialsDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentialsDto.getUsername(), credentialsDto.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (DisabledException e) {
            throw new UnauthorizedException("Your email is not verified yet");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Invalid credentials");
        } catch (Exception e) {
            throw new UnauthorizedException("An error occurred while trying to authenticate");
        }
    }

}
