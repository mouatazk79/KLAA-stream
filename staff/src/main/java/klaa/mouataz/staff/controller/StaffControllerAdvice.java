package klaa.mouataz.staff.controller;

import klaa.mouataz.shared.exception.ErrorDTO;
import klaa.mouataz.shared.exception.GlobalExceptionHandler;
import klaa.mouataz.staff.exception.StaffException;
import klaa.mouataz.staff.exception.StaffNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class StaffControllerAdvice extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {StaffException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(StaffException exception){
        log.error(exception.getMessage(),exception);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();

    }
    @ResponseBody
    @ExceptionHandler(value = {StaffNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(StaffNotFoundException exception){
        log.error(exception.getMessage(),exception);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build();

    }
}
