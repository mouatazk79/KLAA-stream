package klaa.mouataz.documents.model;

import klaa.mouataz.documents.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@org.springframework.data.mongodb.core.mapping.Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    @Id
    private Long id;
    private String name;
    private DocumentType documentType;
    private String documentURL;


}
