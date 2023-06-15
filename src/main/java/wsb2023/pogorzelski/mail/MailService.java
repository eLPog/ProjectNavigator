package wsb2023.pogorzelski.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MailService {

    final private JavaMailSender javaMailSender;

    public void sendMail(Mail mail) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setSubject(mail.getTitle());
            mimeMessageHelper.setText(mail.getContent());
            mimeMessageHelper.setSentDate(new Date());

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Cant send Email. Error!");
        }
    }

}
