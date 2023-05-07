package shop.mtcoding.village.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.dto.payment.PaymentDTO;
import shop.mtcoding.village.model.cardData.CardDataRepository;
import shop.mtcoding.village.model.metadata.MetaData;
import shop.mtcoding.village.model.metadata.MetaRepository;
import shop.mtcoding.village.model.payment.BootPatRepository;
import shop.mtcoding.village.model.payment.BootPay;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.util.status.PaymentStatus;

import java.util.Map;
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
    public BootPay 구매요청(ReceiptDTO receiptDTO) {

        Optional<Payment> paymentOptional = paymentRepository.findByReceiptIdAndTotalPrice(receiptDTO.getReceiptId(), receiptDTO.getPrice());

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
        return paymentRepository.save(new Payment(paymentDTO.getReceiptId(), paymentDTO.getPrice()));
    }
}