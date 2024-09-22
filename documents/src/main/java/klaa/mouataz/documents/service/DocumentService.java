package klaa.mouataz.documents.service;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.repos.DocumentRepository;
import klaa.mouataz.shared.DocumentPayload;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<Document> getVisibleDocuments(int page, int size) {

        Pageable pageable= PageRequest.of(page, size);
        Page<Document> documents =documentRepository.findDocumentsByVisibleIsTrue(pageable);
        List<Document> listDocuments=documents.toList();
        return new PageResponse<>(
                listDocuments,
                documents.getNumber(),
                documents.getSize(),
                documents.isFirst(),
                documents.isLast()
        );
    }

    public Document getDocument(String id) {
        return documentRepository.getDocumentById(id);
    }
    public Document updateDocument(Document document) {
        Document existedDocument= documentRepository.getDocumentById(document.getId());
        existedDocument.setName(document.getName());
        existedDocument.setDocumentType(document.getDocumentType());
        existedDocument.setDocumentURL(document.getDocumentURL());

        System.out.println(existedDocument);
        return documentRepository.save(existedDocument);
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
