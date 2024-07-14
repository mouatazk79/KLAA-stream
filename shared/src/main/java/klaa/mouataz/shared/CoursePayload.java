package klaa.mouataz.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursePayload {
    private Long teacherId;
    private String name;
    private String courseURL;
}
