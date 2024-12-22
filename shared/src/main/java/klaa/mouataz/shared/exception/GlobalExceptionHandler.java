package klaa.mouataz.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
@ResponseBody
@ExceptionHandler(value = {Exception.class})
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ErrorDTO handleException(Exception exception){
    log.error(exception.getMessage(), exception);
    return ErrorDTO.builder()
            .message("unexpected error")
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();

}
}
