package shop.mtcoding.village.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import shop.mtcoding.village.interfaceTest.AbstractIntegrated;
import shop.mtcoding.village.model.host.Host;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HostControllerTest extends AbstractIntegrated {

    @Test
    void postFail() throws Exception {
        this.mockMvc.perform(
                        post ("/host"
                                , 0
                        ) .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andDo(document("host-fail", responseFields(getFailResponseField())));

    }

    @Test
    public void saveTest() {
        Host host = new Host();
        host.setBusinessNum("123456789");
        host.setNickName("test nickName");


        Assertions.assertEquals(host.getBusinessNum(), "123456789");
        Assertions.assertEquals(host.getNickName(), "test nickName");

    }


}
