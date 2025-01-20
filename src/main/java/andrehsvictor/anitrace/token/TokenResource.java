package andrehsvictor.anitrace.token;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.anitrace.authentication.dto.AuthenticationDto;
import andrehsvictor.anitrace.token.dto.AccessTokenDto;
import andrehsvictor.anitrace.token.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TokenResource {

    private final TokenService tokenService;

    @PostMapping("/api/v1/token")
    public AccessTokenDto request(@Valid @RequestBody AuthenticationDto authenticationDto) {
        return tokenService.request(authenticationDto);
    }

    @PostMapping("/api/v1/token/refresh")
    public AccessTokenDto refresh(@Valid @RequestBody TokenDto tokenDto) {
        return tokenService.refresh(tokenDto);
    }

    @PostMapping("/api/v1/token/revoke")
    public ResponseEntity<Void> revoke(@Valid @RequestBody TokenDto tokenDto) {
        tokenService.revoke(tokenDto);
        return ResponseEntity.noContent().build();
    }
}
