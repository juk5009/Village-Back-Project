//package shop.mtcoding.village.core.advice;
//
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//
//import shop.mtcoding.village.core.exception.*;
//
//import io.sentry.Sentry;
//
//import shop.mtcoding.village.dto.ResponseDTO;
//
//
//@RestControllerAdvice
//public class MyExceptionAdvice {
//
//    @ExceptionHandler(Exception400.class)
//    public ResponseEntity<?> badRequest(Exception400 e) {
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, e.getMessage(), "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        if (e.getCause() instanceof ConstraintViolationException) {
//            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
//            String message = cve.getSQLException().getMessage();
//            if (message.contains("UK_2DLFG6WVNXBOKNKP9D1H75ICB_INDEX_2")) {
//                // email이 이미 존재하는 경우에 대한 예외 처리
//                ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 409, "이미 존재하는 이메일입니다.", "Null");
//                return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
//            } else {
//                // 기타 데이터 위반 예외 처리
//                ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, "데이터 위반 예외가 발생했습니다.", "Null");
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            // 기타 예외 처리
//            ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @ExceptionHandler(Exception500.class)
//    public ResponseEntity<?> serverError(Exception500 e) {
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, "일시적인 서버 오류입니다.", "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//    @ExceptionHandler(MyConstException.class)
//    public ResponseEntity<?> error(MyConstException e) {
//        String detail = e.getMessage();
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, detail, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> serverError(Exception e){
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//}