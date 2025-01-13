package andrehsvictor.anitrace.token;

import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.jwt.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RevokedTokenRepository revokedTokenRepository;
    private final JwtService jwtService;
    
}
