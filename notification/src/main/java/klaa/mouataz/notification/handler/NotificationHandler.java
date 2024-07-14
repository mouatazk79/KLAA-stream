package klaa.mouataz.notification.handler;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.service.NotificationService;
import klaa.mouataz.shared.CoursePayload;
import klaa.mouataz.shared.DocumentPayload;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class NotificationHandler {
    private final NotificationService notificationService;
    @KafkaHandler
    public void handleNotification(CoursePayload payload){
        log.info("course notification handled: "+payload);
        Notification notification=Notification
                .builder()
                .subject(payload.getName())
                .description("teacher course : "+payload.getTeacherId()+", courseURL: "+payload.getCourseURL())
                .build();
        notificationService.addNotification(notification);
    }
   @KafkaHandler
    public void handleNotification(DocumentPayload payload){
        log.info("Document notification handled: "+payload);
       Notification notification=Notification
               .builder()
               .subject(payload.getName())
               .description("documentURL: "+payload.getDocumentURL())
               .build();
       notificationService.addNotification(notification);
    }
}
