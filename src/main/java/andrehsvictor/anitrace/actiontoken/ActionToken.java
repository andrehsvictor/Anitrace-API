package andrehsvictor.anitrace.actiontoken;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@RedisHash("action_token")
public class ActionToken implements Serializable {

    private static final long serialVersionUID = -75893655273969648L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    private String token;

    @Indexed
    private UUID userId;

    @Indexed
    @Enumerated(EnumType.STRING)
    private ActionTokenType type;

    @TimeToLive
    private Long ttl;

}
