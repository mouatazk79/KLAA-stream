package klaa.mouataz.notification.model;


import klaa.mouataz.shared.auditing.MongoAudit;
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
public class Notification extends MongoAudit {

    @Id
    @Indexed(unique=true)
    private String id;
    private String subject;
    private String description;
}
