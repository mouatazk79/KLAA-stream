package klaa.mouataz.videos.service;

import klaa.mouataz.videos.model.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoService {
    Flux<Video> getVideos();
    Mono<Video> getVideo(Long id);
    void deleteVideo(Long id);
    Mono<Video> updateVideo(Video video);

}
