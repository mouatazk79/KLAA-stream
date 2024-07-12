package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.service.ReactiveVideoStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private final ReactiveVideoStreamingService reactiveVideoStreamingService;
    @GetMapping(value = "/{titleName}" , produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String titleName) {
        return reactiveVideoStreamingService.getVideoStreaming(titleName);
    }
}
