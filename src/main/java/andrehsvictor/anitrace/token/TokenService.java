package andrehsvictor.anitrace.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.authentication.AuthenticationService;
import andrehsvictor.anitrace.authentication.dto.AuthenticationDto;
import andrehsvictor.anitrace.jwt.JwtService;
import andrehsvictor.anitrace.security.impl.UserDetailsImpl;
import andrehsvictor.anitrace.token.dto.AccessTokenDto;
import andrehsvictor.anitrace.token.dto.TokenDto;
import andrehsvictor.anitrace.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RevokedTokenRepository revokedTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AccessTokenDto request(AuthenticationDto authenticationDto) {
        Authentication authentication = authenticationService.authenticate(authenticationDto);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        String subject = user.getId().toString();
        return accessTokenDto(subject);
    }

    public AccessTokenDto refresh(TokenDto tokenDto) {
        Jwt refreshToken = jwtService.decode(tokenDto.getToken());
        refreshTokenRepository.deleteById(refreshToken.getId());
        return accessTokenDto(refreshToken.getSubject());
    }

    public void revoke(TokenDto tokenDto) {
        Jwt accessToken = jwtService.decode(tokenDto.getToken());
        revokedTokenRepository.save(accessToken);
    }

    private AccessTokenDto accessTokenDto(String subject) {
        Jwt accessToken = jwtService.issueAccessToken(subject);
        Jwt refreshToken = jwtService.issueRefreshToken(subject);
        refreshTokenRepository.save(refreshToken);
        Long expiresIn = accessToken.getExpiresAt().getEpochSecond() - accessToken.getIssuedAt().getEpochSecond();
        return AccessTokenDto.builder()
                .accessToken(accessToken.getTokenValue())
                .refreshToken(refreshToken.getTokenValue())
                .expiresIn(expiresIn)
                .build();
    }

}
