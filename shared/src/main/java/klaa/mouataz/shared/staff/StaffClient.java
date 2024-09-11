package klaa.mouataz.shared.staff;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "staff")
public interface StaffClient {
    @PostMapping("/api/v1/staffs")
     void addStaff(@RequestBody StaffDto newStaff);
    @GetMapping("/api/v1/staffs//username/{staffUserName}")
    StaffDto getStaffByUserName(@PathVariable("staffUserName") String userName);
}
