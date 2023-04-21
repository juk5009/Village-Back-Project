package shop.mtcoding.village.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AdminCustomException extends RuntimeException {

    private HttpStatus status;

    public AdminCustomException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public AdminCustomException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }
}