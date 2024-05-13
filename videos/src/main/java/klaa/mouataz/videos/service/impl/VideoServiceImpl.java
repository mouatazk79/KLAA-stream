package klaa.mouataz.videos.service.impl;

import klaa.mouataz.videos.model.Video;
import klaa.mouataz.videos.repos.VideoRepository;
import klaa.mouataz.videos.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public Flux<Video> getVideos() {
        return null;
    }

    @Override
    public Mono<Video> getVideo(Long id) {
        return null;
    }

    @Override
    public void deleteVideo(Long id) {

    }

    @Override
    public Mono<Video> updateVideo(Video video) {
        return null;
    }

    @Override
    public Mono<Video> addVideo(Video video) {
        return null;
    }
}
