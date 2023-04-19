package shop.mtcoding.village.mock;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.mtcoding.village.controller.host.HostController;
import shop.mtcoding.village.service.HostService;

@WebMvcTest(HostController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PlaceMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private HostService hostService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("호스트 신청 성공")
    @WithMockUser
    void saveHost() throws Exception {

    }

}
