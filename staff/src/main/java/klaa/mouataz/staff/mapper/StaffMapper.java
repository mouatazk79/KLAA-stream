package klaa.mouataz.staff.mapper;

import klaa.mouataz.staff.dto.StaffDTO;
import klaa.mouataz.staff.model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE= Mappers.getMapper(StaffMapper.class);

    StaffDTO StaffToStaffDTO (Staff staff);
    Staff StaffDTOToStaff (StaffDTO staffDTO);

}
