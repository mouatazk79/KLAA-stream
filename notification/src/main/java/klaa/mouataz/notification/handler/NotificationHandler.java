package klaa.mouataz.notification.handler;

import klaa.mouataz.shared.CoursePayload;
import klaa.mouataz.shared.DocumentPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
        id = "notificationID",
        groupId = "notification.group.id",
        topics = "notification",
        containerFactory = "concurrentKafkaListenerContainerFactory"
)
@Slf4j
public class NotificationHandler {
    @KafkaHandler
    public void handleNotification(CoursePayload payload){
        log.info("course notification handled: "+payload);
    }
   @KafkaHandler
    public void handleNotification(DocumentPayload payload){
        log.info("Document notification handled: "+payload);
    }
}
