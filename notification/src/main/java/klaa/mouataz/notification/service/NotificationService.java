package klaa.mouataz.notification.service;

import klaa.mouataz.notification.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications();
    Notification getNotification(Long id);
    void  deleteNotification(Long id);
    Notification updateNotification(Long id,Notification notification);
    Notification addNotification(Notification notification);
}
