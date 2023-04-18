package shop.mtcoding.village.mock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.mtcoding.village.controller.file.FileInfoController;
import shop.mtcoding.village.dto.file.request.FileInfoSaveRequest;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.file.FileType;
import shop.mtcoding.village.service.FileInfoService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileInfoController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class FileInfoMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private FileInfoService fileInfoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("파일정보 조회 (페이지)")
    @WithMockUser
    void getPage() throws Exception {
        Pageable pageable = PageRequest.of(1, 10);
        Page<FileInfo> page = new PageImpl<>(
                List.of(
                        new FileInfo(1L, FileType.PLACE),
                        new FileInfo(2L, FileType.MAP)
                )
        );


        // given
        given(this.fileInfoService.getPage(pageable)).willReturn(page);


        // When
        ResultActions perform = this.mvc.perform(
                get("/fileInfo?page={page}&size={size}", 1, 10)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].type").value("PLACE"))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].type").value("MAP"))
        ;
    }

    @Test
    @DisplayName("파일정보 조회 실패")
    @WithMockUser
    void getFileInfoFail() throws Exception {

        // given
        long id = 1L;
        given(this.fileInfoService.getFileInfo(id)).willReturn(Optional.empty());


        // When
        ResultActions perform = this.mvc.perform(
                get("/fileInfo/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.detail").value("파일 정보를 받을 수 없습니다."))
        ;
    }

    @Test
    @DisplayName("파일정보 조회")
    @WithMockUser
    void getFileInfo() throws Exception {

        // given
        long id = 0;
        given(this.fileInfoService.getFileInfo(id))
                .willReturn(
                        Optional.of(new FileInfo(1L, FileType.PLACE))
                );


        // When
        ResultActions perform = this.mvc.perform(
                get("/fileInfo/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("PLACE"))
        ;
    }

    @Test
    @DisplayName("파일정보 저장 실패")
    @WithMockUser
    void saveFileInfoFail() throws Exception {

        // given
        FileInfoSaveRequest request = new FileInfoSaveRequest("PLACE");

        // When
        ResultActions perform = this.mvc.perform(
                post("/fileInfo")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.detail").value("파일 출처를 입력해주세요"))
        ;
    }

    @Test
    @DisplayName("파일정보 저장 성공")
    @WithMockUser
    void saveFileInfo() throws Exception {


        // given
        FileInfoSaveRequest request = new FileInfoSaveRequest("PLACE");
        given(this.fileInfoService.save(request))
                .willReturn(
                        request.toEntity()
                );

        // When
        ResultActions perform = this.mvc.perform(
                post("/fileInfo")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        perform
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.type").value("PLACE"))
        ;
    }


}
