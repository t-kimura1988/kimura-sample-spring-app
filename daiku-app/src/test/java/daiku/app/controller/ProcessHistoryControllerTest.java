package daiku.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import daiku.app.controller.input.processHistory.ProcessHistoryCreateParam;
import daiku.app.controller.input.processHistory.ProcessHistoryUpdateCommentParam;
import daiku.app.controller.input.processHistory.ProcessHistoryUpdateStatusParam;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.model.res.ProcessHistorySearchModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProcessHistoryControllerTest extends ControllerBaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void process_history_create_アカウント存在なし_正常() throws Exception {
        var request = ProcessHistoryCreateParam.builder()
                .processId(1L)
                .comment("process title")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/create")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_create_アカウント種別不正() throws Exception {
        var request = ProcessHistoryCreateParam.builder()
                .processId(1L)
                .comment("process title")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/create")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "0"
    })
    void process_history_create_authorization_パラメータ不正(Long processId) throws Exception {

        var request = ProcessHistoryCreateParam.builder()
                .processId(processId)
                .comment("process title")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_create_authorization_正常() throws Exception {
        var request = ProcessHistoryCreateParam.builder()
                .processId(1L)
                .comment("process title")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }



    @Test
    void process_history_update_comment_アカウント存在なし_正常() throws Exception {
        var request = ProcessHistoryUpdateCommentParam.builder()
                .processHistoryId(1L)
                .comment("comment").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/comment")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_update_comment_アカウント種別不正() throws Exception {
        var request = ProcessHistoryUpdateCommentParam.builder()
                .processHistoryId(1L)
                .comment("comment").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/comment")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ",test",
            "0,test",
            "1,"
    })
    void process_history_update_comment_authorization_パラメータ不正(Long processId, String comment) throws Exception {
        var request = ProcessHistoryUpdateCommentParam.builder()
                .processHistoryId(processId)
                .comment(comment).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/comment")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_update_comment_authorization_正常() throws Exception {
        var request = ProcessHistoryUpdateCommentParam.builder()
                .processHistoryId(1L)
                .comment("test comment").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/comment")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }



    @Test
    void process_history_update_status_アカウント存在なし_正常() throws Exception {
        var request = ProcessHistoryUpdateStatusParam.builder()
                .processId(1L)
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/status")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_update_status_アカウント種別不正() throws Exception {
        var request = ProcessHistoryUpdateStatusParam.builder()
                .processId(1L)
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/status")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ",0,0",
            "0,0,0",
            "1,a,0",
            "1,0,a"
    })
    void process_history_update_status_authorization_パラメータ不正(Long processId, String processStatusString, String priorityString) throws Exception {
        var request = ProcessHistoryUpdateStatusParam.builder()
                .processId(processId)
                .processStatus(ProcessStatus.of(processStatusString))
                .priority(ProcessPriority.of(priorityString)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/status")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_history_update_status_authorization_正常() throws Exception {
        var request = ProcessHistoryUpdateStatusParam.builder()
                .processId(1L)
                .processStatus(ProcessStatus.PROBLEM)
                .priority(ProcessPriority.HEIGHT).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process-history/update/status")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }


    @Test
    void process_history_list_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process-history/list")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_history_list_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process-history/list")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_history_list_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process-history/list")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("processId", "1");

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        Type collectionType = new TypeToken<Collection<ProcessHistorySearchModel>>(){}.getType();
        List<ProcessHistorySearchModel> dto = new Gson().fromJson(resBodyAsString, collectionType);

        // 10件以上
        assertTrue(dto.size() > 0);

    }


}
