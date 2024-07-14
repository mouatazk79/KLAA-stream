package klaa.mouataz.documents.service;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.repos.DocumentRepository;
import klaa.mouataz.shared.DocumentPayload;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final NewTopic topic;
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }
    public List<Document> getVisibleDocuments() {

        return documentRepository.findDocumentsByVisibleIsTrue();
    }

    public Document getDocument(String id) {
        return documentRepository.getDocumentById(id);
    }


    public Document updateDocument(String id, Document document) {

       // Document existedDocument= documentRepository.getDocumentById(id);
        return documentRepository.save(document);
    }

    public void deleteDocument(String id) {
        documentRepository.deleteById(id);
    }

    public Document addDocument(Document document) {
        DocumentPayload documentPayload = DocumentPayload.builder()
                .name(document.getName())
                .documentURL(document.getDocumentURL())
                .build();
        kafkaTemplate.send(topic.name(),documentPayload);
        return documentRepository.save(document);
    }
}
