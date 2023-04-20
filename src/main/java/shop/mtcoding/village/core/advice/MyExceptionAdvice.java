package shop.mtcoding.village.core.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import shop.mtcoding.village.core.exception.*;

import io.sentry.Sentry;

import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.exception.Exception400;


@RestControllerAdvice
public class MyExceptionAdvice {


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> ex(Exception e){
//        Sentry.captureException(e);
//        String message = e.getMessage();
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(1, message, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
<<<<<<< HEAD

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        Sentry.captureException(e);
        String message = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1,400, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> ex400(Exception e){

        Sentry.captureException(e);
        String message = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1,400, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> ex400(Exception e){
//
//        Sentry.captureException(e);
//        String message = e.getMessage();
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(1, message, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//
//    }
//
////    @ExceptionHandler(Exception400.class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    public ResponseEntity<?> ex401(MyValidationException e){
////        String errMsg = e.getErroMap().toString();
////        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
////        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
////    }
//
//    @ExceptionHandler(MyValidationException.class)
//    public ResponseEntity<?> error(MyValidationException e){
//        Sentry.captureException(e);
//        String errMsg = e.getErroMap().toString();
//        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
//        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
//    }
<<<<<<< HEAD

    @ExceptionHandler(MyValidationException.class)
    public ResponseEntity<?> error(MyValidationException e){
        Sentry.captureException(e);
        String errMsg = e.getErroMap().toString();
        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
        return new ResponseEntity<>(new ResponseDTO<>(-1,400,devideMsg,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyConstException.class)
    public ResponseEntity<?> error(MyConstException e){
        String detail = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1,400, detail, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyViolationException.class)
    public ResponseEntity<?> error(MyViolationException e){
        String msg = e.getMessage();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1,400, msg, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomApiException e) {
        Sentry.captureException(e);
        return new ResponseEntity<>(new ResponseDTO<>(-1,400, e.getMessage(), null), e.getStatus());
    }


}
