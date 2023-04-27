package shop.mtcoding.village.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    private final Logger logger = LoggerFactory.getLogger(PayController.class);

    @PostMapping("/bootpaytest")
    public String bootpaytest(@RequestBody String json){
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            logger.error("Error occurred while processing JSON data+ jonnnnnnnnnnnnnnnnnnnnn\n\n" + json, e);
        }
        return "json";
    }

    @GetMapping("/bootpaytest1")
    public String bootpaytest1(){
        return "json";
    }
    
}



