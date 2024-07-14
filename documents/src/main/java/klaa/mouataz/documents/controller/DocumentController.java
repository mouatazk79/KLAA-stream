package klaa.mouataz.documents.controller;

import klaa.mouataz.documents.model.Document;
import klaa.mouataz.documents.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentService documentService;
    @GetMapping
    public List<Document> getDocuments(){
        return documentService.getDocuments();
    }
    @GetMapping("/{id}")
    public Document getDocument(@PathVariable("id") String id){
        return documentService.getDocument(id);
    }
    @PatchMapping("/{id}")
    public Document updateDocument(@PathVariable("id") String id,@RequestBody Document document){
        return documentService.updateDocument(id,document);
    }
    @PostMapping
    public Document addDocuments(@RequestBody Document document){
        return documentService.addDocument(document);
    }
    @DeleteMapping("/{id}")
    public void deleteDocuments(@PathVariable("id") String id){
        documentService.deleteDocument(id);
    }
}
