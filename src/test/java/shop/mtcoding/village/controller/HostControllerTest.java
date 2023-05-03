package shop.mtcoding.village.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.controller.host.HostController;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.service.HostService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class HostControllerTest{

    @Autowired
    private HostController hostController;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HostService hostService;

    @Test
    public void saveTest() {
        Host host = new Host();
        host.setBusinessNum("123456789");
        host.setNickName("test nickName");

        Host result = hostRepository.save(host);

        Assertions.assertEquals(result.getBusinessNum(), "123456789");
        Assertions.assertEquals(result.getNickName(), "test nickName");

    }


}
