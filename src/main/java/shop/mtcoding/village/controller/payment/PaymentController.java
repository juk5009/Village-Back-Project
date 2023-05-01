package shop.mtcoding.village.controller.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.service.PaymentService;

import java.util.HashMap;

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

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReceiptDTO receiptDTO) throws JsonProcessingException {

        objectMapper.writeValueAsString(receiptDTO);

        paymentService.구매요청(receiptDTO);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        System.out.println("디버그 : " + map);

        return ResponseEntity.ok(map);
    }
}
