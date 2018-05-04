package ua.com.jcoh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jcoh.entity.User;
import ua.com.jcoh.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void send(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessage.setFrom(new InternetAddress("andriiandrosiukk@gmail.com"));
            helper.setTo(user.getEmail());
            helper.setSubject("i love you");
            helper.setText("hello " + user.getUsername()+ ", visit our site ", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
