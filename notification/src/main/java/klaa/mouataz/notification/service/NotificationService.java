package klaa.mouataz.notification.service;

import klaa.mouataz.notification.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications();
    Notification getNotification(String id);
    void  deleteNotification(String id);
    Notification updateNotification(String id,Notification notification);
    Notification addNotification(Notification notification);
}
