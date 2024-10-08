package klaa.mouataz.documents.repos;

import klaa.mouataz.documents.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends MongoRepository<Document,String> {

    Document getDocumentById(String id);

    Page<Document> findDocumentsByVisibleIsTrue(Pageable pageable);

}
