package klaa.mouataz.mailsender.controller;

import klaa.mouataz.mailsender.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;
    @PostMapping("/{email}")
    public void mailSend(@PathVariable("email")String email){
        mailService.sendEmail(email,"","");
    }
}
