package klaa.mouataz.videos.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException(String message) {
        super(message);
    }

    public VideoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
