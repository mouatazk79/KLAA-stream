package klaa.mouataz.videos.service;
import com.google.protobuf.ByteString;
import com.klaa.stream.Video;
import com.klaa.stream.VideoRequest;
import com.klaa.stream.VideoStreamingServiceGrpc;
import io.grpc.stub.StreamObserver;
import klaa.mouataz.videos.repos.VideoRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;


@GrpcService
@RequiredArgsConstructor
public class VideoService extends VideoStreamingServiceGrpc.VideoStreamingServiceImplBase  {

    private final VideoRepository videoRepository;
    @Override
    public void streamVideo(VideoRequest request, StreamObserver<Video> responseObserver) {
        Optional<klaa.mouataz.videos.model.Video> newVideo=videoRepository.pull(request.getVideoName());
        Video streamVideo=Video.newBuilder()
                        .setId(newVideo.get().size())
                                .setChunk(ByteString.copyFrom(newVideo.get().videoByteBuffer()))
                                        .build();


        responseObserver.onNext(streamVideo);

    }
}
