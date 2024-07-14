package klaa.mouataz.documents.service;

import klaa.mouataz.documents.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> getDocuments();
    Document getDocument(String id);
    Document updateDocument(String id,Document document);
    void deleteDocument(String id);
    Document addDocument(Document document);

}
