package ea.finalproject.notificationservicev2.service;


import ea.finalproject.notificationservicev2.Model.Notification;
import ea.finalproject.notificationservicev2.repository.PaymentInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledNotification {
    private static final Logger log = LoggerFactory.getLogger(ScheduledNotification.class);
    @Autowired
    PaymentInformationRepository paymentInformationRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @Scheduled(cron = "0 0 0 * * ?")
    public void print() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        LocalDate today = LocalDate.now();
        List<Notification> notifications = paymentInformationRepository.findAllByExpiryDate(
                LocalDate.of(today.getYear(), today.getMonth(), today.getDayOfMonth() + 1).toString());

        for (Notification notification : notifications) {
            try {
                mimeMessageHelper.setTo(notification.getEmail());
                mimeMessageHelper.setSubject("Your subscription package is expiring soon !!!");
                mimeMessageHelper.setText("Dear " + notification.getFirstName()
                        + "\nYour subscription package " + notification.getPlan()
                        + " for " + notification.getServiceProvider()
                        + " is expiring on " + notification.getExpiryDate()
                        + " . Please buy a new subscription pack to avoid inconveniences. "
                        + "\n\n Thank you."
                        + "\n\nEA-fanatics");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            javaMailSender.send(mimeMessage);
        }

        log.info("Scheduled notification task has executed on " + LocalDate.now());
    }
}
