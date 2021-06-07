package edu.ap.group10.leapwebapp.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailServiceImpl implements MailService{
    @Autowired
    JavaMailSender mailSender;

    public void sendMail(Mail mail) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(new InternetAddress("leapwebapp@gmail.com", "leapwebapp.com"));
            mimeMessageHelper.setTo(InternetAddress.parse(mail.getReceiver()));
            mimeMessageHelper.setText(mail.getContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());
        }
        catch (Exception e) {
            log.error("Exception: " + e);
        }
    }
    
}
