//package shop.mtcoding.village.core.advice;
//
//import io.sentry.Sentry;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import shop.mtcoding.village.core.exception.*;
//import shop.mtcoding.village.dto.ResponseDTO;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@RestControllerAdvice
//public class MyExceptionAdvice {
//
//   @ExceptionHandler(Exception400.class)
//   public ResponseEntity<?> badRequest(Exception400 e) {
//       ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, e.getMessage(), "Null");
//       return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//   }
//   @ExceptionHandler(DataIntegrityViolationException.class)
//   public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//       if (e.getCause() instanceof ConstraintViolationException) {
//           ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
//           String message = cve.getSQLException().getMessage();
//           if (message.contains("UK_2DLFG6WVNXBOKNKP9D1H75ICB_INDEX_2")) {
//               // email이 이미 존재하는 경우에 대한 예외 처리
//               ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 409, "이미 존재하는 이메일입니다.", "Null");
//               return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
//           } else {
//               // 기타 데이터 위반 예외 처리
//               ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, "데이터 위반 예외가 발생했습니다.", "Null");
//               return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//           }
//       } else {
//           // 기타 예외 처리
//           ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
//           return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//       }
//   }
//
//    // unAuthorized
//    @ExceptionHandler(Exception401.class)
//    public ResponseEntity<?> unAuthorized(Exception401 e){
//        Sentry.captureException(e);
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 401, "인증이 안되었습니다. 다시 확인해주세요", "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
//    }
//
//    // forbidden
//    @ExceptionHandler(Exception403.class)
//    public ResponseEntity<?> forbidden(Exception403 e){
//        Sentry.captureException(e);
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 403, "권한이 없습니다. 다시 확인해주세요", "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
//    }
//
//    // notFound
//    // 자원을 못 찾은 경우
//    @ExceptionHandler(Exception404.class)
//    public ResponseEntity<?> notFound(Exception404 e){
//        Sentry.captureException(e);
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 404, "경로가 잘못 되었습니다 다시 확인해주세요", "Null");
//        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
//    }
//
//
//    @ExceptionHandler(Exception500.class)
//    public ResponseEntity<?> serverError(Exception500 e) {
//       ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, "일시적인 서버 오류입니다.", "Null");
//       return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//   }
//
//   @ExceptionHandler(MyConstException.class)
//   public ResponseEntity<?> error(MyConstException e) {
//       String detail = e.getMessage();
//       List<String> data = Arrays.asList();
//       ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(1, 200, detail, data);
//       return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//   }
//
//   @ExceptionHandler(Exception.class)
//   public ResponseEntity<?> serverError(Exception e){
//       ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
//       return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//   }
//
//}
//
