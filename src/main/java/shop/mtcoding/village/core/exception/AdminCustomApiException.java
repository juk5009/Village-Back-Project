package shop.mtcoding.village.core.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AdminCustomApiException extends RuntimeException{
    
    private HttpStatus status;

    public AdminCustomApiException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
    public AdminCustomApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
