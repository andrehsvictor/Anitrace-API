package andrehsvictor.anitrace.email;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.email.dto.SendEmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void send(SendEmailDto sendEmailDto) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(sendEmailDto.getTo());
            helper.setSubject(sendEmailDto.getSubject());
            helper.setText(sendEmailDto.getText(), true);
            javaMailSender.send(message);
        } catch (MailException | MessagingException e) {
            throw new RuntimeException("An error occurred while trying to send the email");
        }
    }

}
