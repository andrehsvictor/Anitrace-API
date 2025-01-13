package andrehsvictor.anitrace.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }
}
