package klaa.mouataz.notification.repos;

import klaa.mouataz.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Notification findNotificationById(Long id);
}
