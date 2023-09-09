package klaa.mouataz.mailsender.service;

public interface MailService {
    void sendEmail(String toEmail,String subject,String body);
}
