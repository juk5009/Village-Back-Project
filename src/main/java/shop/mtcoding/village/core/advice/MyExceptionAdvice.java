package shop.mtcoding.village.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.exception.MyValidationException;
import shop.mtcoding.village.dto.ResponseDTO;



@RestControllerAdvice
public class MyExceptionAdvice {

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ProblemDetail onException400(Exception400 exception400) {
//        return ProblemDetail.forStatusAndDetail(
//                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
//                exception400.getMessage()
//        );
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(500, e.getMessage().toLowerCase(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception e){
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(500, "", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> ex400(Exception e){
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> ex401(MyValidationException e){
//        String errMsg = e.getErroMap().toString();
//        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
//        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MyValidationException.class)
    public ResponseEntity<?> error(MyValidationException e){
        String errMsg = e.getErroMap().toString();
        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyConstException.class)
    public ResponseEntity<?> error(MyConstException e){
        String detail = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(400, detail, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
