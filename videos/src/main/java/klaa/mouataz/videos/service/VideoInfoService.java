package klaa.mouataz.videos.service;

import klaa.mouataz.shared.page.PageResponse;
import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.repos.VideoInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<VideoInfo> getAllVideosInfo(int page, int size) {

        Pageable pageable= PageRequest.of(page, size);
        Page<VideoInfo> notifications =videoInfoRepository.findAll(pageable);
        List<VideoInfo> listNotifications=notifications.toList();
        return new PageResponse<>(
                listNotifications,
                notifications.getNumber(),
                notifications.getSize(),
                notifications.isFirst(),
                notifications.isLast()
        );
    }
}
