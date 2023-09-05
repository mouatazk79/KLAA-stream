package klaa.mouataz.staff.controller;

import klaa.mouataz.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class StaffController {
    private final StaffService staffService;
}
