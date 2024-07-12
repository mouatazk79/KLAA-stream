package klaa.mouataz.notification.repos;

import klaa.mouataz.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,Long> {
    Notification findNotificationById(Long id);
}
