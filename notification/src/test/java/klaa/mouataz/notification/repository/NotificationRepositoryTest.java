package klaa.mouataz.notification.repository;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.repos.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class NotificationRepositoryTest {

    @Autowired
     NotificationRepository notificationRepository;
    @Test
    void findNotificationById(){
        Notification notification=Notification.builder()
                .id(1L)
                .subject("subject")
                .description("test")
                .build();
        notificationRepository.save(notification);
        Notification existNotification=notificationRepository.findNotificationById(1L);

        assertThat(existNotification).isEqualTo(notification);

            }
    @Test
    void addNotification(){
        Notification notification=Notification.builder()
                .id(1L)
                .subject("subject")
                .description(null)
                .build();
        notificationRepository.save(notification);
        assertThat(notificationRepository.findNotificationById(1L)).isEqualTo(notification);

    }

}
