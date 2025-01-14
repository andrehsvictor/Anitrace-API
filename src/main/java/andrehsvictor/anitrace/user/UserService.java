package andrehsvictor.anitrace.user;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UUID getAuthenticatedUserUuid() {
        Jwt jwt = ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getToken();
        return UUID.fromString(jwt.getSubject());
    }

    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, "ID", id));
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
