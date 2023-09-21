package klaa.mouataz.documents.controller;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentService documentService;
    @GetMapping
    public Flux<Document> getDocuments(){
        return documentService.getDocuments();
    }
    @PostMapping
    public Mono<Document> addDocuments(@RequestBody Document document){
        return documentService.addDocument(document);
    }
}
