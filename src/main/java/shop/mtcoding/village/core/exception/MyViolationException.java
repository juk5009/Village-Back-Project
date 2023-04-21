package shop.mtcoding.village.core.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class MyViolationException extends DataIntegrityViolationException {

    public MyViolationException(String msg) {
        super(msg);
    }
}
