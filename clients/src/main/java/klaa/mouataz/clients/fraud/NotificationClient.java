package klaa.mouataz.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping
    public Notification addNotification(@RequestBody Notification notification);
}
