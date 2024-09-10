package klaa.mouataz.documents.model;

import klaa.mouataz.documents.enums.DocumentType;
import klaa.mouataz.shared.auditing.MongoAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@org.springframework.data.mongodb.core.mapping.Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document extends MongoAudit {
    @Id
    @Indexed(unique=true)
    private String id;

    private String name;
    private String description;
    private DocumentType documentType;
    private String documentURL;
    private boolean visible;


}
