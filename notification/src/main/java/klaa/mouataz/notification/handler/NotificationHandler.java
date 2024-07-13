package klaa.mouataz.notification.handler;

import klaa.mouataz.shared.notification.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
        id = "notificationID",
        groupId = "notification",
        topics = "notification",
        containerFactory = "concurrentKafkaListenerContainerFactory"
)
@Slf4j
public class NotificationHandler {

    @KafkaHandler
    public void handleNotification(@Payload Course course){
        log.info("course notification handled: "+course);
    }
}
