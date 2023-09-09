package klaa.mouataz.documents.repos;

import klaa.mouataz.documents.model.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends ReactiveMongoRepository<Document,Long> {
}
