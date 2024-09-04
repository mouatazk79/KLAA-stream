package klaa.mouataz.notification.controller;

import klaa.mouataz.notification.model.Notification;
import klaa.mouataz.notification.service.NotificationService;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping
    public PageResponse<Notification> getAllVisibleDocuments(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return notificationService.getNotifications(page, size);
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
