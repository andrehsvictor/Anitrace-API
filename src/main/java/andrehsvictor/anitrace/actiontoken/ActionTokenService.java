package andrehsvictor.anitrace.actiontoken;

import java.time.Duration;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.actiontoken.dto.CreateActionTokenDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActionTokenService {

    private final ActionTokenRepository actionTokenRepository;

    @Value("${anitrace.security.token.action-token.lifespan:1h}")
    private Duration duration = Duration.ofHours(1);

    public ActionToken create(CreateActionTokenDto createActionTokenDto) {
        ActionToken actionToken = new ActionToken();
        actionToken.setToken(generateToken());
        actionToken.setType(createActionTokenDto.getType());
        actionToken.setUserId(createActionTokenDto.getUserId());
        Long ttl = duration.getSeconds();
        actionToken.setTtl(ttl);
        return actionTokenRepository.save(actionToken);
    }

    public boolean existsByUserIdAndType(UUID userId, ActionTokenType type) {
        return actionTokenRepository.existsByUserIdAndType(userId, type);
    }

    private String generateToken() {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(UUID.randomUUID().toString().getBytes());
    }

}
