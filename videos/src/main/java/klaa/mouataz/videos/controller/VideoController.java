package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.Video;
import klaa.mouataz.videos.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @GetMapping
    public Flux<Video> getVideos(){
        return videoService.getVideos();
    }
    @PostMapping
    public Mono<Video> addVideo(){
        Video video=Video.builder().id(1L).title("hhh").teacherId(1L).description("hhhhhh").build();
      return videoService.addVideo(video);
    }
}
