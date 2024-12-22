package klaa.mouataz.notification.repos;

import klaa.mouataz.notification.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {
   Optional<Notification> findNotificationById(String id);

}
