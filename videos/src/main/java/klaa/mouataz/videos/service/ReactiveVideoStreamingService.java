package klaa.mouataz.videos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveVideoStreamingService {
    private final ResourceLoader resourceLoader;

    public Mono<Resource> getVideoStreaming(String titleName) {
        return Mono.fromSupplier(() -> resourceLoader.getResource("classpath:assets/" + titleName + ".mp4"));
    }
}
