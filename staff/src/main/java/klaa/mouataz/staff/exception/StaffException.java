package klaa.mouataz.staff.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StaffException extends RuntimeException{
    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Throwable cause) {
        super(message, cause);
    }
}
