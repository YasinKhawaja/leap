package edu.ap.group10.leapwebapp.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
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
            mimeMessageHelper.setFrom(new InternetAddress(mail.getSender(), "leapwebapp.com"));
            mimeMessageHelper.setTo(InternetAddress.parse(mail.getReceiver()));
            mimeMessageHelper.setText(mail.getContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());
        }
        catch (MessagingException e) {
            log.debug("Messaging Exception: " + e);
        }
        catch (UnsupportedOperationException e) {
            log.debug("Operation Exception: " + e);
        }
        catch (UnsupportedEncodingException e) {
            log.debug("Encoding Exception: " + e);
        }
    }
    
}
