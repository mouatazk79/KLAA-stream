package klaa.mouataz.videos.repos;

import klaa.mouataz.videos.model.VideoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoInfoRepository  extends MongoRepository<VideoInfo,String> {
    List<VideoInfo> getVideoInfoByCourseId(String id);

}
