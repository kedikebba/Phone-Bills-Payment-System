package ea.finalproject.notificationservicev2.repository;

import ea.finalproject.notificationservicev2.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentInformationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByExpiryDate(String localDate);
}
