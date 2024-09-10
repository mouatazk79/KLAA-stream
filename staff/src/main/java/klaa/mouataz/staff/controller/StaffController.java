package klaa.mouataz.staff.controller;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.shared.staff.StaffDto;
import klaa.mouataz.staff.model.Staff;
import klaa.mouataz.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staffs")
public class StaffController {
    private final StaffService staffService;
    @GetMapping
    public PageResponse<Staff> getAllStaffs(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return staffService.getAllStaffs(page, size);
    }
    @GetMapping("/{staffId}")
    public Staff getStaffById(@PathVariable("staffId") Long id){
        return staffService.getStaffById(id);
    }
    @DeleteMapping("/{staffId}")
    public void deleteStaffById(@PathVariable("staffId") Long id){
        staffService.deleteStaffById(id);
    }
    @PatchMapping("/{staffId}")
    public Staff updateStaffById(@PathVariable("staffId") Long id, @RequestBody Staff newStaff) {
        return staffService.updateStaffById(id,newStaff);
    }
    @PostMapping
    public void addStaff(@RequestBody StaffDto newStaff) {
         staffService.addStaff(newStaff);
    }


    }
