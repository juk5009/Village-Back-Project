package shop.mtcoding.village.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.dto.payment.PaymentDTO;
import shop.mtcoding.village.model.cardData.CardDataRepository;
import shop.mtcoding.village.model.metadata.MetaRepository;
import shop.mtcoding.village.model.payment.*;
import shop.mtcoding.village.util.status.PaymentStatus;

import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final BootPatRepository bootPatRepository;

    private final CardDataRepository cardDataRepository;

    private final MetaRepository metaRepository;

    public PaymentService(PaymentRepository paymentRepository, BootPatRepository bootPatRepository, CardDataRepository cardDataRepository, MetaRepository metaRepository) {
        this.paymentRepository = paymentRepository;
        this.bootPatRepository = bootPatRepository;
        this.cardDataRepository = cardDataRepository;
        this.metaRepository = metaRepository;
    }

    @Transactional
    public Bootpay 구매요청(ReceiptDTO receiptDTO) {

        Optional<Payment> paymentOptional = paymentRepository.findByOrderIdAndOrderNameAndTotalPrice(receiptDTO.getOrderId(), receiptDTO.getOrderName(), receiptDTO.getPrice());

        if (paymentOptional.isEmpty()) {
            throw new Exception400("payment","결제 정보가 올바르지 않습니다.");
        }

        Payment payment = paymentOptional.get();

        payment.setStatus(PaymentStatus.COMPLETE);
        paymentRepository.save(payment);

        cardDataRepository.save(receiptDTO.getCard_data().toEntity());

        metaRepository.save(receiptDTO.getMetadata().toEntity());

        return bootPatRepository.save(receiptDTO.toEntity());
    }

    @Transactional
    public void 결제내역삭제(Payment payment) {
        try {
            paymentRepository.delete(payment);
        }catch (Exception e){
            throw new Exception500("예약내역 삭제 오류" + e.getMessage());
        }
    }

    @Transactional
    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }
    public Payment 결제검증(PaymentDTO paymentDTO) {

        paymentDTO.setStatus(PaymentStatus.WAIT);
        return paymentRepository.save(new Payment(paymentDTO.getOrderId(), paymentDTO.getOrderName(), paymentDTO.getPrice()));
    }

    public void 결제취소() {

        try {
            Bootpay bootpay = new Bootpay("6447b7393049c8001d9e06dc", "Ud1mwlNgLWGdL4mC6xdGKHXY3sP6Yg/Qit19ZZ2JLHc=");
            HashMap token = bootpay.getAccessToken();
            if(token.get("error_code") != null) { //failed
                return;
            }
            Cancel cancel = new Cancel();
            cancel.setReceiptId("628b2206d01c7e00209b6087");
            cancel.setCancleUsername("관리자");
            cancel.setCancleMessage("테스트 결제");

            HashMap res = bootpay.receiptCancel(cancel);
            if(res.get("error_code") == null) { //success
                System.out.println("receiptCancel success: " + res);
            } else {
                System.out.println("receiptCancel false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}