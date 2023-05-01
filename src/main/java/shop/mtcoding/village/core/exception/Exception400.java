package shop.mtcoding.village.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.bootpay.ValidDTO;

@Getter
public class Exception400 extends RuntimeException {

    private String key;
    private String value;

    public Exception400(String key, String value) {
        super(value);
        this.key = key;
        this.value = value;
    }

    public ResponseDTO<?> body(){
        ValidDTO validDTO = new ValidDTO(key, value);
        return new ResponseDTO<>(-1,400, "BadRequest", validDTO);
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}
