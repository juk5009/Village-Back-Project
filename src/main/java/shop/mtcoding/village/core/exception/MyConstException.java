package shop.mtcoding.village.core.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class MyConstException extends IllegalArgumentException {
    public MyConstException(String msg){

        super(msg);
    }
}
