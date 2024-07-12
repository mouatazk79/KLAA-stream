package klaa.mouataz.documents.service;

import klaa.mouataz.documents.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> getDocuments();
    Document getDocument(Long id);
    Document updateDocument(Long id,Document document);
    void deleteDocument(Long id);
    Document addDocument(Document document);

}
