package klaa.mouataz.videos.repos;

import klaa.mouataz.videos.model.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<Video,Long> {
}
