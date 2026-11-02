package daiku.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import daiku.app.controller.input.process.ProcessCreateParam;
import daiku.app.controller.input.process.ProcessDateUpdateParam;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.model.res.ProcessSearchModel;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProcessControllerTest extends ControllerBaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void process_create_アカウント存在なし_正常() throws Exception {
        var request = ProcessCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/create")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_create_アカウント種別不正() throws Exception {
        var request = ProcessCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/create")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ", title, body, 0, 0",
            "1, title, body, a, 0",
            "1, title, body, 0, a",
    })
    void process_create_authorization_パラメータ不正(Long goalId, String title, String body, String processStatusString, String priorityString) throws Exception {
        var request = ProcessCreateParam.builder()
                .goalId(goalId)
                .goalCreateDate(LocalDate.now())
                .title(title)
                .body(body)
                .processStatus(ProcessStatus.of(processStatusString))
                .priority(ProcessPriority.of(priorityString)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/create")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_create_authorization_正常() throws Exception {
        var request = ProcessCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }


    @Test
    void process_update_アカウント存在なし_正常() throws Exception {
        var request = ProcessCreateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_update_アカウント種別不正() throws Exception {
        var request = ProcessCreateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ",1, title, body, 0, 0",
            "1,, title, body, 0, 0",
            "1,1, title, body, a, 0",
            "1,1, title, body, 0, a",
    })
    void process_update_authorization_パラメータ不正(Long processId, Long goalId, String title, String body, String processStatusString, String priorityString) throws Exception {
        var request = ProcessCreateParam.builder()
                .processId(processId)
                .goalId(goalId)
                .goalCreateDate(LocalDate.now())
                .title(title)
                .body(body)
                .processStatus(ProcessStatus.of(processStatusString))
                .priority(ProcessPriority.of(priorityString)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_update_authorization_正常() throws Exception {
        var request = ProcessCreateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .title("process title")
                .body("process body")
                .processStatus(ProcessStatus.NEW)
                .priority(ProcessPriority.LOW).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }





    @Test
    void process_list_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/list")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_list_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/list")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_list_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/list")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        Type collectionType = new TypeToken<Collection<GoalSearchModel>>(){}.getType();
        List<GoalSearchModel> dto = new Gson().fromJson(resBodyAsString, collectionType);

        // 10件以上
        assertTrue(dto.size() > 10);

    }


    @Test
    void process_data_update_アカウント存在なし_正常() throws Exception {
        var request = ProcessDateUpdateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .processStartDate(LocalDate.now())
                .processEndDate(LocalDate.now().plusDays(1)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update/process-date")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_date_update_アカウント種別不正() throws Exception {
        var request = ProcessDateUpdateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .processStartDate(LocalDate.now())
                .processEndDate(LocalDate.now().plusDays(1)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update/process-date")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            ", 1, 2023-09-10, 2023-09-15",
            "1, , 2023-09-10, 2023-09-15",
            "1,1, , 2023-09-10",
            "1,1, 2023-09-10, ",
    })
    void process_date_update_authorization_パラメータ不正(Long processId, Long goalId, LocalDate startDate, LocalDate endDate) throws Exception {

        var request = ProcessDateUpdateParam.builder()
                .processId(processId)
                .goalId(goalId)
                .goalCreateDate(LocalDate.now())
                .processStartDate(startDate)
                .processEndDate(endDate).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update/process-date")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void process_date_update_authorization_正常() throws Exception {
        var request = ProcessDateUpdateParam.builder()
                .processId(1L)
                .goalId(1L)
                .goalCreateDate(LocalDate.now())
                .processStartDate(LocalDate.now())
                .processEndDate(LocalDate.now().plusDays(1)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/process/update/process-date")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }





    @Test
    void process_detail_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("processId", "1")
                .param("goalCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_detail_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/detail")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("processId", "1")
                .param("goalCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void process_detail_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/process/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("processId", "1")
                .param("goalCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        ProcessSearchModel dto = new Gson().fromJson(resBodyAsString, ProcessSearchModel.class);

        assertNotNull(dto);

    }
}
