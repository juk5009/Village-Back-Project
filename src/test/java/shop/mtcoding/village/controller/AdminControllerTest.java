//package shop.mtcoding.village.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.FieldDescriptor;
//import org.springframework.security.test.context.support.TestExecutionEvent;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import shop.mtcoding.village.interfaceTest.AbstractIntegrated;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@DisplayName("AdminController 테스트")
//public class AdminControllerTest extends AbstractIntegrated {
//
//    @Test
//    @DisplayName("어드민 메인 전체보기")
//    @WithUserDetails(value = "Bob@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//    void getPage() throws Exception {
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//
//        this.mockMvc.perform(
//                        get("/admin/main")
//                                .params(params)
//                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                )
//                .andExpect(status().isOk())
//                .andDo(
//                        document("admin-list",
//                                responseFields(getPlaceDetailResponseField())
//                        )
//
//                );
//    }
//
//    private FieldDescriptor[] getPlaceDetailResponseField() {
//        return new FieldDescriptor[]{
//                fieldWithPath("code").description("응답 코드"),
//                fieldWithPath("status").description("응답 상태"),
//                fieldWithPath("msg").description("응답 메시지"),
//        };
//    }
//}
