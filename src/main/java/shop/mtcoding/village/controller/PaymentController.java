package shop.mtcoding.village.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.ReceiptDTO;
import shop.mtcoding.village.model.metadata.MetaData;
import shop.mtcoding.village.model.payment.BootPay;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.service.PaymentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    private final ObjectMapper objectMapper;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReceiptDTO receiptDTO) throws JsonProcessingException {

        System.out.println("리퀘스트 잘 들어왔는지? " + receiptDTO);

        String s = objectMapper.writeValueAsString(receiptDTO);
        System.out.println("오브젝트매퍼 역할 됐는지? " + s);

        BootPay 구매요청 = paymentService.구매요청(receiptDTO);
        System.out.println("서비스에서 잘 됐는지?" + 구매요청);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        System.out.println("디버그 : " + map);

        return ResponseEntity.ok(map);
    }
}
