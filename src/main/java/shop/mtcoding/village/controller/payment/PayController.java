package shop.mtcoding.village.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.village.dto.ResponseDTO;


@RestController
public class PayController {

    private final Logger logger = LoggerFactory.getLogger(PayController.class);

    @PostMapping("/bootpaytest")
    public ResponseEntity<?> bootpaytest(@RequestBody String json){
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            logger.error("Error occurred while processing JSON data+ \n\n" + json, e);
        }
        return ResponseEntity.ok().body(json);
    }

    @PostMapping("/returnjson")
    public ResponseEntity<?> bootpaytest1(@RequestBody String json){
        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(json);
        return ResponseEntity.ok().body(responseDTO);
    }
}