package klaa.mouataz.videos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoInfo  {
    @Id
    @Indexed(unique=true)
    private String id;
    private String courseId;
    private String name;
    private String duration;
    private String path;

}
