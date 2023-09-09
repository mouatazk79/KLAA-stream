package klaa.mouataz.documents.service;

import klaa.mouataz.documents.model.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentService {
    Flux<Document> getDocuments();
    Mono<Document> getDocument(Long id);
    Mono<Document> updateDocument(Long id,Document document);
    void deleteDocument(Long id);
    Mono<Document> addDocument(Document document);

}
