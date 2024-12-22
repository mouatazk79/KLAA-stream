package klaa.mouataz.documents.service;

import klaa.mouataz.documents.exception.DocumentNotFoundException;
import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.repos.DocumentRepository;
import klaa.mouataz.shared.DocumentPayload;
import klaa.mouataz.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

        Optional<Document> document=documentRepository.getDocumentById(id);
        if(document.isEmpty()){
            log.error("document not found with id: {}",id);
            throw new DocumentNotFoundException("document not found with id:"+id);
        }
        return document.get();
    }
    public Document updateDocument(Document document) {
        Optional<Document> existedDocument= documentRepository.getDocumentById(document.getId());
        if(existedDocument.isPresent()){
            existedDocument.get().setName(document.getName());
            existedDocument.get().setDocumentType(document.getDocumentType());
            existedDocument.get().setDocumentURL(document.getDocumentURL());
        }else {
            log.error("document with id: {} not found",document.getId());
            throw new DocumentNotFoundException("document with id"+ document.getId()+"not found");
        }
        log.info("document with id: {} updated",document.getId());
        return documentRepository.save(existedDocument.get());
    }
    public void deleteDocument(String id) {
        log.info("document with id: {} deleted",id);
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
