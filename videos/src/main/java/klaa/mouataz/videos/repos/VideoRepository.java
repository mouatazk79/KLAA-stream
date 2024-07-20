package klaa.mouataz.videos.repos;

import klaa.mouataz.videos.model.Video;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class VideoRepository{
    private final Map<String, Video> videoLibrary = new HashMap<>();
    public void put(String name, MultipartFile file) throws IOException {
        var size = (int) file.getSize();
        var byteBuffer = ByteBuffer.allocateDirect(size);
        try (ReadableByteChannel channel = Channels.newChannel(file.getInputStream())) {
            channel.read(byteBuffer);
        }
        byteBuffer.position(0);
        videoLibrary.put(name, new Video(size, byteBuffer));
    }

    public Optional<Video> pull(String name) {
        return Optional.of(videoLibrary.get(name));
    }
}
