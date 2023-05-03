package shop.mtcoding.village.controller.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.PaymentService;

import java.util.HashMap;
import java.util.Optional;

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
    public ResponseEntity<HashMap<Object,Object>> save(@RequestBody ReceiptDTO receiptDTO) throws JsonProcessingException {

        objectMapper.writeValueAsString(receiptDTO);

        paymentService.구매요청(receiptDTO);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        System.out.println("디버그 : " + map);

        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
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
