package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.payment.BootPatRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BootPayService {

    private final BootPatRepository bootPatRepository;



}
