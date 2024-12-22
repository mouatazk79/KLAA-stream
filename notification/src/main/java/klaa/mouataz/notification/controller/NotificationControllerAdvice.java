package klaa.mouataz.notification.controller;

import klaa.mouataz.notification.exception.NotificationException;
import klaa.mouataz.notification.exception.NotificationNotFoundException;
import klaa.mouataz.shared.exception.ErrorDTO;
import klaa.mouataz.shared.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class NotificationControllerAdvice extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {NotificationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(NotificationException exception){
        log.error(exception.getMessage(),exception);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();

    }
    @ResponseBody
    @ExceptionHandler(value = {NotificationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(NotificationNotFoundException exception){
        log.error(exception.getMessage(),exception);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build();

    }
}
