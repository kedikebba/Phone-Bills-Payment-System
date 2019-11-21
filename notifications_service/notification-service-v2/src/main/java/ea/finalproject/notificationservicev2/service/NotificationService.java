package ea.finalproject.notificationservicev2.service;

import com.google.gson.Gson;
import ea.finalproject.notificationservicev2.Model.Notification;
import ea.finalproject.notificationservicev2.repository.PaymentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PaymentInformationRepository paymentInformationRepository;

    @KafkaListener(topics = "Subscription", groupId = "group_id")
    public void consume(String message) throws IOException {
        System.out.println("\n\n\n" + message);
        Gson gson = new Gson();
        Notification notification = gson.fromJson(message, Notification.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(notification.getEmail());
            mimeMessageHelper.setSubject("Your mobile subscription has been received and processed successfully");
            mimeMessageHelper.setText("Dear " + notification.getFirstName()
                    + "\n Your subscription for " + notification.getPlan()
                    + "\n  at a cost of USD. " + notification.getAmount()
                    + "  has been processed successfully on " + notification.getSubscriptionDate()
                    + "  and will expire on " + notification.getExpiryDate()
                    + "\n\n Thank you for your continued support \n\nEA fanatics");


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
        paymentInformationRepository.save(notification);

    }
}
