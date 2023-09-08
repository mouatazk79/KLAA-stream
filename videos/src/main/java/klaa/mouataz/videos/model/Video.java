package klaa.mouataz.videos.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Video {
    @Id
    private Long id;
    private Long teacherId;
    private String title;
    private String description;
}
