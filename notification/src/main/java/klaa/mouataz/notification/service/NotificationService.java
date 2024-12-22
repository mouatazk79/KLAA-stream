package klaa.mouataz.notification.service;

import klaa.mouataz.notification.exception.NotificationNotFoundException;
import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.repos.NotificationRepository;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

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
        Optional<Notification> notification=notificationRepository.findNotificationById(id);
        if(notification.isEmpty()){
            log.error("notification not found with id: {}",id);
            throw new NotificationNotFoundException("notification not found with id:"+id);
        }

        return notification.get();
    }

    public void deleteNotification(String id) {
        log.info("notification with id: {} deleted",id);
        notificationRepository.deleteById(id);
    }

    public Notification updateNotification(String id, Notification notification) {
        Optional<Notification> existedNotification=notificationRepository.findNotificationById(id);
        if(existedNotification.isPresent()){
            existedNotification.get().setSubject(notification.getSubject());
            existedNotification.get().setDescription(notification.getDescription());
        }else {
            log.error("notification with id: {} not found",notification.getId());
            throw new NotificationNotFoundException("notification with id"+ notification.getId()+"not found");
        }
        log.info("notification with id: {} updated",notification.getId());
        return notificationRepository.save(existedNotification.get());
    }

    public Notification addNotification(Notification notification) {
        log.info("notification with id: {} added",notification.getId());
        return notificationRepository.save(notification);
    }
}
