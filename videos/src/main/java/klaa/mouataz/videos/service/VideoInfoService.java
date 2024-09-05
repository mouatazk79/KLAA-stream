package klaa.mouataz.videos.service;

import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.repos.VideoInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoInfoService {
    private final VideoInfoRepository videoInfoRepository;
    public List<VideoInfo> getVideosInfoByCourseId(String courseId){
        return videoInfoRepository.getVideoInfoByCourseId(courseId);
    }
    public VideoInfo addVideoInfo(VideoInfo info){
        return videoInfoRepository.save(info);
    }
}
