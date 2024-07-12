package klaa.mouataz.notification.service;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.repos.NotificationRepository;
import klaa.mouataz.notification.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

public class NotificationServiceTest {
    @Mock
    private NotificationRepository notificationRepository;
    private NotificationService notificationService;
    @Captor
    private ArgumentCaptor<Notification> notificationArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService=new NotificationServiceImpl(notificationRepository);
    }

    @Test
    void getNotifications() {
        List<Notification> notifications=notificationService.getNotifications();
        assertThat(notifications.size()).isZero();
    }

    @Test
    void getNotification() {

    }

    @Test
    void deleteNotification() {
        Notification notification=Notification.builder()
                .id(3L)
                .subject("test")
                .description("test")
                .build();
        notificationService.addNotification(notification);
        notificationService.deleteNotification(3L);
        assertThat(notificationService.getNotification(3L)).isEqualTo(null);
    }

    @Test
    void updateNotification() {
    }

    @Test
    void addNotification() {
        Long id=22L;
        Notification notification=Notification.builder()
                .id(id)
                .subject("test")
                .description("test")
                .build();
//        given(Objects.equals(notificationRepository.findNotificationById(id).getDescription(), ""))
//                .willReturn(null);
        then(notificationRepository).should().save(notificationArgumentCaptor.capture());
        Notification notificationArgumentCaptorValue =notificationArgumentCaptor.getValue();
        assertThat(notificationArgumentCaptorValue).isEqualToComparingFieldByField(notification);
    }
}
