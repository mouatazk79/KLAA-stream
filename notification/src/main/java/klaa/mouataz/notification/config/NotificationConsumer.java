package klaa.mouataz.notification.config;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "notification.queue")
    public void consume(Notification notification){
        System.out.println("@@@@@@@@@ notification added @@@@@@@@@@@@@@@@");
        notificationService.addNotification(notification);
    }

}
