package klaa.mouataz.shared.staff;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "staff")
public interface StaffClient {
    @PostMapping("/api/v1/staffs")
     void addStaff(@RequestBody StaffDto newStaff);
}
