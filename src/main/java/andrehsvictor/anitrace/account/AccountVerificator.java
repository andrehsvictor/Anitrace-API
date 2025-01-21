package andrehsvictor.anitrace.account;

import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.account.dto.SendActionEmailDto;
import andrehsvictor.anitrace.actiontoken.ActionToken;
import andrehsvictor.anitrace.actiontoken.ActionTokenService;
import andrehsvictor.anitrace.actiontoken.ActionTokenType;
import andrehsvictor.anitrace.actiontoken.dto.CreateActionTokenDto;
import andrehsvictor.anitrace.email.EmailService;
import andrehsvictor.anitrace.email.dto.SendEmailDto;
import andrehsvictor.anitrace.exception.ResourceConflictException;
import andrehsvictor.anitrace.file.FileService;
import andrehsvictor.anitrace.user.User;
import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountVerificator {

    private final AccountService accountService;
    private final UserService userService;
    private final EmailService emailService;
    private final ActionTokenService actionTokenService;
    private final FileService fileService;

    public void sendVerificationEmail(SendActionEmailDto sendActionEmailDto) {
        User user = userService.getByEmail(sendActionEmailDto.getEmail());
        if (user.isEmailVerified()) {
            throw new ResourceConflictException("Email already verified");
        }
        CreateActionTokenDto createActionTokenDto = new CreateActionTokenDto();
        createActionTokenDto.setType(ActionTokenType.VERIFY_EMAIL);
        createActionTokenDto.setUserId(user.getId());

        ActionToken actionToken = actionTokenService.create(createActionTokenDto);

        String emailContent = fileService.read("classpath:templates/email-verification.html");
        emailContent = emailContent.replace("{{link}}",
                sendActionEmailDto.getUrl() + "?token=" + actionToken.getToken());

        SendEmailDto sendEmailDto = new SendEmailDto();
        sendEmailDto.setTo(sendActionEmailDto.getEmail());
        sendEmailDto.setSubject("Verify your email");
        sendEmailDto.setText(emailContent);

        emailService.send(sendEmailDto);

    }
}
