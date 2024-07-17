package klaa.mouataz.courses.model;

import klaa.mouataz.shared.auditing.MongoAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course extends MongoAudit implements Serializable {
    @Id
    @Indexed(unique=true)
    private String id;
    private Long teacherId;
    private String name;
    private String field;
    private String description;
    private String courseURL;
    private boolean visible;


}
