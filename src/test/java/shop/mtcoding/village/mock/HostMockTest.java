package shop.mtcoding.village.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.mtcoding.village.controller.host.HostController;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.service.HostService;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HostController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class HostMockTest {


    @Autowired
    MockMvc mvc;

    @MockBean
    private HostService hostService;

    private final ObjectMapper objectMapper = new ObjectMapper();


//    @Test
//    @DisplayName("호스트 신청 성공")
//    @WithMockUser
//    void saveHost() throws Exception {
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

//         // given
//         HostSaveRequest saveHost = new HostSaveRequest("김호스트", "부산 부산진구 중앙대로 688 한준빌딩 2층", "481-145612");
//         given(this.hostService.호스트신청(saveHost))
//                 .willReturn(saveHost.toEntity());


//         // When
//         ResultActions perform = this.mvc.perform(
//                 post("/host")
//                         .content(objectMapper.writeValueAsString(saveHost))
//                         .accept(MediaType.APPLICATION_JSON_VALUE)
//                         .contentType(MediaType.APPLICATION_JSON_VALUE)
//                         .with(csrf())
//         );

//         // Then
//         perform
//                 .andExpect(status().isOk())
//                 .andDo(print())
//                 .andExpect(jsonPath("$.hostName").value("김호스트"))
//                 .andExpect(jsonPath("$.address").value("부산 부산진구 중앙대로 688 한준빌딩 2층"))
//                 .andExpect(jsonPath("$.businessNum").value("481-12-45612"));
//     }

//     @Test
//     @DisplayName("호스트 신청 Valid 실패")
//     @WithMockUser
//     void updateValidFail1() throws Exception {


//         // given
//         HostSaveRequest saveDto = new HostSaveRequest("", "내용", "1234567891");

//         // When
//         ResultActions perform = this.mvc.perform(
//                 post("/host")
//                         .content(objectMapper.writeValueAsString(saveDto))
//                         .accept(MediaType.APPLICATION_JSON_VALUE)
//                         .contentType(MediaType.APPLICATION_JSON_VALUE)
//                         .with(csrf())
//         );


//         // Then
//         perform
//                 .andExpect(status().is5xxServerError())
//                 .andDo(print())
//                 .andExpect(jsonPath("$.hostName").value("호스트 이름을 입력해주세요."));
//     }
 }
