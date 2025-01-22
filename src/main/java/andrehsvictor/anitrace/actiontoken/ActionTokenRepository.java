package andrehsvictor.anitrace.actiontoken;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface ActionTokenRepository extends CrudRepository<ActionToken, UUID> {

    boolean existsByUserIdAndType(UUID userId, ActionTokenType type);

    Optional<ActionToken> findByToken(String token);

}
