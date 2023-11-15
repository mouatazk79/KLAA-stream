package klaa.mouataz.mailsender;

import klaa.mouataz.mailsender.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Boot implements CommandLineRunner {
    private final MailService mailService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************* mail ******************");
        mailService.sendEmail("mouatazk79@gmail.com","test","test");
    }
}
