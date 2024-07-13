package klaa.mouataz.shared.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private Long id;
    private Long teacherId;
    private String name;
    private String field;
    private String description;
    private String courseURL;

}
