package klaa.mouataz.documents.service.impl;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.repos.DocumentRepository;
import klaa.mouataz.documents.service.DocumentService;
import klaa.mouataz.shared.DocumentPayload;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final NewTopic topic;
    @Override
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Document getDocument(String id) {
        return documentRepository.getDocumentById(id);
    }

    @Override
    public Document updateDocument(String id, Document document) {

       // Document existedDocument= documentRepository.getDocumentById(id);
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(String id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Document addDocument(Document document) {
        DocumentPayload documentPayload = DocumentPayload.builder()
                .name(document.getName())
                .documentURL(document.getDocumentURL())
                .build();
        kafkaTemplate.send(topic.name(),documentPayload);
        return documentRepository.save(document);
    }
}
