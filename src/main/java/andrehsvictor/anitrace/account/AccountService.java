package andrehsvictor.anitrace.account;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.account.dto.AccountDto;
import andrehsvictor.anitrace.account.dto.CreateAccountDto;
import andrehsvictor.anitrace.account.dto.EditAccountDto;
import andrehsvictor.anitrace.account.dto.SendActionEmailDto;
import andrehsvictor.anitrace.actiontoken.ActionTokenService;
import andrehsvictor.anitrace.actiontoken.ActionTokenType;
import andrehsvictor.anitrace.actiontoken.dto.CreateActionTokenDto;
import andrehsvictor.anitrace.email.EmailService;
import andrehsvictor.anitrace.exception.ResourceConflictException;
import andrehsvictor.anitrace.user.User;
import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;
    private final AccountVerificator accountVerificator;

    public AccountDto create(CreateAccountDto createAccountDto) {
        if (userService.existsByUsername(createAccountDto.getUsername())) {
            throw new ResourceConflictException("Username already exists");
        }
        if (userService.existsByEmail(createAccountDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = accountMapper.createAccountDtoToUser(createAccountDto);
        String encodedPassword = passwordEncoder.encode(createAccountDto.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return accountMapper.userToAccountDto(user);
    }

    public AccountDto edit(EditAccountDto editAccountDto) {
        UUID userId = userService.getAuthenticatedUserUuid();
        User user = userService.getById(userId);
        user = accountMapper.updateUserFromEditAccountDto(editAccountDto, user);
        user = userService.save(user);
        return accountMapper.userToAccountDto(user);
    }

    public AccountDto get() {
        UUID userId = userService.getAuthenticatedUserUuid();
        User user = userService.getById(userId);
        return accountMapper.userToAccountDto(user);
    }

    public void delete() {
        UUID userId = userService.getAuthenticatedUserUuid();
        userService.deleteById(userId);
    }

    public void sendActionEmail(SendActionEmailDto sendActionEmailDto) {
        switch (sendActionEmailDto.getAction()) {
            case "VERIFY_EMAIL":
                accountVerificator.sendVerificationEmail(sendActionEmailDto);
                break;
            default:
                throw new IllegalArgumentException("Invalid action");
        }
    }

}
