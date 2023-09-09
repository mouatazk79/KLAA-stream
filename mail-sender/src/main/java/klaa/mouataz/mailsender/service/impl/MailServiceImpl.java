package klaa.mouataz.mailsender.service.impl;

import klaa.mouataz.mailsender.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

    }
}
