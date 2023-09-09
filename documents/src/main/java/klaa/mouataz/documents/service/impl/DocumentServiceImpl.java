package klaa.mouataz.documents.service.impl;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.repos.DocumentRepository;
import klaa.mouataz.documents.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    @Override
    public Flux<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Mono<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public Mono<Document> updateDocument(Long id, Document document) {

        return null;
    }

    @Override
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Mono<Document> addDocument(Document document) {
        return documentRepository.save(document);
    }
}
