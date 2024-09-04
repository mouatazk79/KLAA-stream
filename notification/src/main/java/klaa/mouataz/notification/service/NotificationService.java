package klaa.mouataz.notification.service;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.repos.NotificationRepository;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

//    public List<Notification> getNotifications() {
//        return notificationRepository.findAll();
//    }
    public PageResponse<Notification> getNotifications(int page, int size) {

        Pageable pageable= PageRequest.of(page, size);
        Page<Notification> notifications =notificationRepository.findAll(pageable);
        List<Notification> listNotifications=notifications.toList();
        return new PageResponse<>(
                listNotifications,
                notifications.getNumber(),
                notifications.getSize(),
                notifications.isFirst(),
                notifications.isLast()
        );
    }

    public Notification getNotification(String id) {

        return notificationRepository.findNotificationById(id);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    public Notification updateNotification(String id, Notification notification) {
        Notification existedNotification=notificationRepository.findNotificationById(id);
        existedNotification.setSubject(notification.getSubject());
        existedNotification.setDescription(notification.getDescription());
        return notificationRepository.save(existedNotification);
    }

    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
