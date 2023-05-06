//package shop.mtcoding.village.mock;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import shop.mtcoding.village.controller.file.FileController;
//import shop.mtcoding.village.dto.file.request.FileUpdateRequest;
//import shop.mtcoding.village.model.file.File;
//import shop.mtcoding.village.model.file.FileInfo;
//import shop.mtcoding.village.model.file.FileStatus;
//import shop.mtcoding.village.model.file.FileType;
//import shop.mtcoding.village.service.FileService;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(FileController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//public class FileMockTest {
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    private FileService fileService;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    @DisplayName("파일 조회")
//    @WithMockUser
//    void getPage() throws Exception {
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setId(1L);
//        fileInfo.setType(FileType.PLACE);
//
//
//        Pageable pageable = PageRequest.of(1, 10);
////        Page<File> page = new PageImpl<>(
////                List.of(
////                        new File(1L, fileInfo, "파일이름", "파일URL", FileStatus.WAIT),
////                       new File(2L, fileInfo, "파일이름2", "파일URL2", FileStatus.WAIT)
////               )
////        );
//
//
//        // given
////        given(this.fileService.getPage(pageable)).willReturn(page);
//
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                get("/files?page={page}&size={size}", 1, 10)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.content[0].id").value(1L))
//                .andExpect(jsonPath("$.content[0].fileInfo.id").value(1L))
//                .andExpect(jsonPath("$.content[0].fileInfo.type").value("PLACE"))
//                .andExpect(jsonPath("$.content[0].fileName").value("파일이름"))
//                .andExpect(jsonPath("$.content[0].fileUrl").value("파일URL"))
//                .andExpect(jsonPath("$.content[0].status").value("WAIT"))
//
//                .andExpect(jsonPath("$.content[1].id").value(2L))
//                .andExpect(jsonPath("$.content[1].fileInfo.id").value(1L))
//                .andExpect(jsonPath("$.content[1].fileInfo.type").value("PLACE"))
//                .andExpect(jsonPath("$.content[1].fileName").value("파일이름2"))
//                .andExpect(jsonPath("$.content[1].fileUrl").value("파일URL2"))
//                .andExpect(jsonPath("$.content[1].status").value("WAIT"))
//        ;
//    }
//
//    @Test
//    @DisplayName("파일 조회 실패")
//    @WithMockUser
//    void getFileFail() throws Exception {
//
//        // given
//        long id = 0;
//        given(this.fileService.getFile(id)).willReturn(Optional.empty());
//
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                get("/files/{id}", id)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andExpect(jsonPath("$.msg").value("파일을 넣어주세요"))
//        ;
//    }
//
//    @Test
//    @DisplayName("파일 저장 실패")
//    @WithMockUser
//    void saveFileFail() throws Exception {
//
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setId(1L);
//        fileInfo.setType(FileType.PLACE);
//
//
//        // given
////        FileSaveRequest request = new FileSaveRequest("name", "url");
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                post("/files")
////                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andExpect(jsonPath("$.msg").value("파일 이름을 입력해주세요"))
//        ;
//    }
//
//    @Test
//    @DisplayName("파일 저장 성공")
//    @WithMockUser
//    void saveFile() throws Exception {
//
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setId(1l);
//        fileInfo.setType(FileType.PLACE);
//
//
////        // given
////        FileSaveRequest request = new FileSaveRequest("name", "url");
////        given(this.fileService.save(request))
////                .willReturn(
////                        request.toEntity()
////                );
//
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                post("/files")
////                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.fileName").value("8.jpg"))
//                .andExpect(jsonPath("$.fileUrl").value("shop.mtcoding.com/8.jpg"))
//        ;
//    }
//
//    @Test
//    @DisplayName("공지사항 수정 Valid 실패")
//    @WithMockUser
//    void updateValidFail() throws Exception {
//
//        // given
//        int id = 0;
//        FileUpdateRequest request = new FileUpdateRequest("name", "url", "IMAGE");
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                put("/files/{id}", id)
//                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isBadRequest())
//                .andDo(print())
//                .andExpect(jsonPath("$.detail").value("파일 출처를 입력해주세요"))
//        ;
//    }
//
//    @Test
//    @DisplayName("파일 저장 성공")
//    @WithMockUser
//    void saveFileComplete() throws Exception {
//
//
//        // given
////        FileSaveRequest request = new FileSaveRequest("name", "url");
////        given(this.fileService.save(request))
////                .willReturn(
////                        request.toEntity()
////                );
//
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                post("/files")
////                        .content(objectMapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//
//        // Then
//        perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.fileName").value("제목"))
//                .andExpect(jsonPath("$.fileUrl").value("내용"))
//        ;
//    }
//
//    @Test
//    @DisplayName("파일 삭제")
//    @WithMockUser
//    void deleteFile() throws Exception {
//
//        // given
//        long id = 0;
//        Optional<File> optional = Optional.of(new File());
//        given(this.fileService.getFile(id)).willReturn(optional);
//
//
//        // When
//        ResultActions perform = this.mvc.perform(
//                delete("/files/{id}", id)
//        );
//
//        // Then
//        MvcResult mvcResult = perform
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//        Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "삭제가 완료되었습니다.");
//    }
//}
