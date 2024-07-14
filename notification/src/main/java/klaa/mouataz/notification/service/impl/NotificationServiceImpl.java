package klaa.mouataz.notification.service.impl;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.repos.NotificationRepository;
import klaa.mouataz.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotification(String id) {

        return notificationRepository.findNotificationById(id);
    }

    @Override
    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification updateNotification(String id, Notification notification) {
        Notification existedNotification=notificationRepository.findNotificationById(id);
        existedNotification.setSubject(notification.getSubject());
        existedNotification.setDescription(notification.getDescription());
        return notificationRepository.save(existedNotification);
    }

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
