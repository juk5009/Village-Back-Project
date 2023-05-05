package shop.mtcoding.village.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.interfaceTest.AbstractIntegrated;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserControllerTest")
public class UserControllerTest extends AbstractIntegrated {

    @Test
    @DisplayName("UserLoginTest")
    void userLogin() throws Exception{

        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO("ssar@naver.com", "1234", "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx");


        ResultActions perform = this.mockMvc.perform(
                post("/login")
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("Response:" + response.getHeader("Authorization"));

        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("user-login",
                                requestFields(getUserLoginRequestField()),
                                responseFields().and(getUserLoginField("data.")
                            )
                        )

                );

    }
    @Test
    @DisplayName("HostLoginTest")
    void hostLogin() throws Exception{

        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO("Jane@naver.com", "1234", "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx");


        ResultActions perform = this.mockMvc.perform(
                post("/login")
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("Response:" + response.getHeader("Authorization"));

        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("user-login",
                                requestFields(getUserLoginRequestField()),
                                responseFields().and(getUserLoginField("data.")
                                )
                        )

                );

    }
    @Test
    @DisplayName("AdminLoginTest")
    void adminLogin() throws Exception{

        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO("Bob@naver.com", "1234", "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx");


        ResultActions perform = this.mockMvc.perform(
                post("/login")
                        .content(objectMapper.writeValueAsString(loginDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("Response:" + response.getHeader("Authorization"));

        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("user-login",
                                requestFields(getUserLoginRequestField()),
                                responseFields().and(getUserLoginField("data.")
                                )
                        )

                );

    }
    private FieldDescriptor[] getUserLoginRequestField() {
        return new FieldDescriptor[] {
                fieldWithPath("email").description("이메일"),
                fieldWithPath("password").description("비밀번호"),
                fieldWithPath("targetToken").description("FCM토큰"),
        };
    }

    private FieldDescriptor[] getUserLoginField(String prefix) {
        return new FieldDescriptor[] {
                fieldWithPath("code").description("응답 코드"),
                fieldWithPath("status").description("응답 상태"),
                fieldWithPath("msg").description("응답 메시지"),
                fieldWithPath(prefix+"id").description("Id"),
                fieldWithPath(prefix+"name").description("이름"),
                fieldWithPath(prefix+"email").description("이메일")
        };
    }

}
