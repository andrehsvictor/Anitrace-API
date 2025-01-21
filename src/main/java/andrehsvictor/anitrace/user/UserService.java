package andrehsvictor.anitrace.user;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import andrehsvictor.anitrace.user.dto.UserDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserDto> toDto(Page<User> users) {
        return users.map(userMapper::userToUserDto);
    }

    public UserDto toDto(User user) {
        return userMapper.userToUserDto(user);
    }

    public User getMe() {
        return getById(getAuthenticatedUserUuid());
    }

    public Page<User> getAll(String query, Pageable pageable) {
        return userRepository.findAll(query, pageable);
    }

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
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

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, "email", email));
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
