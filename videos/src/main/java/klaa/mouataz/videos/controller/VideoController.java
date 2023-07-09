package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.Video;
import klaa.mouataz.videos.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @GetMapping
    public Flux<Video> getVideos(){
        return videoService.getVideos();
    }
}
