package shop.mtcoding.village.controller.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.dto.payment.PaymentDTO;
import shop.mtcoding.village.model.payment.BootPay;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.BootPayService;
import shop.mtcoding.village.service.PaymentService;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    private final BootPayService bootPayService;

    private final ObjectMapper objectMapper;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService, BootPayService bootPayService, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.bootPayService = bootPayService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<HashMap<Object,Object>> save(@RequestBody ReceiptDTO receiptDTO) throws JsonProcessingException {

        objectMapper.writeValueAsString(receiptDTO);

        BootPay bootpay = paymentService.구매요청(receiptDTO);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);

        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<?> compare(@RequestBody PaymentDTO paymentDTO) {

        Payment payment = paymentService.결제검증(paymentDTO);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "결제검증완료", payment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        Optional<Payment> payment = paymentService.getPayment(id);
        if (payment.isEmpty()) {
            throw new MyConstException(ReservationConst.notfound);
        }
        paymentService.결제내역삭제(payment.get());
        return new ResponseEntity<>(new ResponseDTO<>(1, 200 , "결제내역 삭제완료", null), HttpStatus.OK);
    }
}
