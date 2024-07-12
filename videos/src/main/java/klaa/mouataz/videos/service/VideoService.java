package klaa.mouataz.videos.service;
import com.google.protobuf.ByteString;
import com.klaa.stream.Video;
import com.klaa.stream.VideoRequest;
import com.klaa.stream.VideoStreamingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@GrpcService
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class VideoService extends VideoStreamingServiceGrpc.VideoStreamingServiceImplBase  {

    private final ReactiveVideoStreamingService reactiveVideoStreamingService;
    @Override
    public void streamVideo(VideoRequest request, StreamObserver<Video> responseObserver) {
        reactiveVideoStreamingService.getVideoStreaming(request.getVideoName())
                .flatMapMany(resource -> {
                    try {
                        InputStream inputStream = resource.getInputStream();
                        byte[] buffer = new byte[4096];
                        List<Video> videoChunks = new ArrayList<>();
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            Video videoChunk = Video.newBuilder()
                                    .setChunk(ByteString.copyFrom(buffer, 0, bytesRead))
                                    .build();
                            videoChunks.add(videoChunk);
                        }
                        inputStream.close();
                        return Flux.fromIterable(videoChunks);
                    } catch (IOException e) {
                        return Flux.error(e);
                    }
                })
                .subscribe(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }
}
