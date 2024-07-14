package klaa.mouataz.notification.controller;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping
    public List<Notification> getNotifications(){
        return notificationService.getNotifications();
    }
    @GetMapping("/{notificationId}")
    public Notification getNotification(@PathVariable("notificationId") String id){
        return notificationService.getNotification(id);
    }
    @PutMapping("/{notificationId}")
    public Notification updateNotification(@PathVariable("notificationId")String id,@RequestBody Notification notification){
        return notificationService.updateNotification(id,notification);
    }
    @PostMapping
    public Notification addNotification(@RequestBody Notification notification){
        return    notificationService.addNotification(notification);
    }
    @DeleteMapping("{notificationId}")
    public void deleteNotification(@PathVariable("notificationId")String id){
        notificationService.deleteNotification(id);
    }


}
