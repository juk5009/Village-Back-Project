package shop.mtcoding.village.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.interfaceTest.AbstractIntegrated;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.place.PlaceAddress;
import shop.mtcoding.village.util.status.HostStatus;
import shop.mtcoding.village.util.status.ReservationStatus;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HostControllerTest extends AbstractIntegrated {

    @Test
    @DisplayName("호스트 신청하기")
    @Transactional
    @WithUserDetails(value = "ssar@naver.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void postReservation () throws Exception {

        PlaceAddress address = new PlaceAddress("도로명주소", "시군구", "우편번호", "상세주소", "경도", "위도");

        HostSaveRequest request = new HostSaveRequest("ssar", "호스트 닉네임", address, "123-45-678", HostStatus.WAIT );
        this.mockMvc.perform(
                        post("/user/host")
//                                .header("Authorization", getUser())
                                .content(objectMapper.writeValueAsString(request))
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("host-save",
                                requestFields(postHostRequestField()),
                                responseFields(postHostnResponseField("data."))
                        )
                );
    }


    private FieldDescriptor[] postHostRequestField() {
        return new FieldDescriptor[]{
                fieldWithPath("hostName").description("호스트 이름"),
                fieldWithPath("nickname").description("호스트 닉네임"),
                fieldWithPath("address.id").description("주소 id"),
                fieldWithPath("address.address").description("도로명주소"),
                fieldWithPath("address.sigungu").description("시군구"),
                fieldWithPath("address.zonecode").description("우편번호"),
                fieldWithPath("address.detailAddress").description("상세주소"),
                fieldWithPath("address.x").description("경도"),
                fieldWithPath("address.y").description("위도"),
                fieldWithPath("businessNum").description("사업자 번호"),
                fieldWithPath("status").description("호스트 상태"),
        };
    }

    private FieldDescriptor[] postHostnResponseField(String prefix) {
        return new FieldDescriptor[]{
                fieldWithPath("code").description("응답 코드"),
                fieldWithPath("status").description("응답 상태"),
                fieldWithPath("msg").description("응답 메시지"),
                fieldWithPath(prefix+"id").description("공간의 id"),
                fieldWithPath(prefix+"user.id").description("유저의 id"),
                fieldWithPath(prefix+"user.name").description("유저의 이름"),
                fieldWithPath(prefix+"user.email").description("유저의 이메일"),
                fieldWithPath(prefix+"user.tel").description("유저의 전화번호"),
                fieldWithPath(prefix+"user.profile").description("유저의 프로필"),
                fieldWithPath(prefix+"user.status").description("유저의 활성화 상태"),
                fieldWithPath(prefix+"user.createdAt").description("가입시간"),
                fieldWithPath(prefix+"nickName").description("공간 제목"),
                fieldWithPath(prefix+"businessNum").description("공간 정보"),
                fieldWithPath(prefix+"status").description("공간 정보"),
                fieldWithPath(prefix+"address").optional().description("공간의 주소"),
                fieldWithPath(prefix+"address.id").optional().description("공간의 주소 id"),
                fieldWithPath(prefix+"address.address").description("도로명주소"),
                fieldWithPath(prefix+"address.sigungu").description("시군구"),
                fieldWithPath(prefix+"address.zonecode").description("우편번호"),
                fieldWithPath(prefix+"address.detailAddress").description("상세주소"),
                fieldWithPath(prefix+"address.x").description("경도"),
                fieldWithPath(prefix+"address.y").description("위도")
        };
    }

}


