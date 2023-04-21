package shop.mtcoding.village.core.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.village.core.exception.AdminCustomApiException;
import shop.mtcoding.village.core.exception.AdminCustomException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AdminCustomException.class)
    public ResponseEntity<?> customException(AdminCustomException e) {
        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(AdminCustomApiException.class)
    public ResponseEntity<?> customApiException(AdminCustomApiException e) {
        return new ResponseEntity<>(new ResponseDTO<>(-1, 500, e.getMessage(), null), e.getStatus());
    }
}