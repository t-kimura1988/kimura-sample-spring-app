package daiku.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import daiku.app.controller.input.goal.GoalArchiveCreateParam;
import daiku.app.controller.input.goal.GoalArchiveUpdatingFlgParameter;
import daiku.app.controller.input.goal.GoalCreateParam;
import daiku.app.controller.output.GoalArchiveDetailResponse;
import daiku.domain.entity.TGoalArchive;
import daiku.domain.enums.PublishLevel;
import daiku.domain.model.res.GoalSearchModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.commons.util.StringUtils;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoalControllerTest extends  ControllerBaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void goal_create_不正_アカウント存在しない() throws Exception {
        var request = GoalCreateParam.builder()
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_create_不正_アカウント種別不正() throws Exception {
        var request = GoalCreateParam.builder()
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({", test aim, test purpose, 2099-09-30"})
    void goal_create_不正パラメータ_title(String title, String aim, String purpose, LocalDate dueDate) throws Exception {
        var request = GoalCreateParam.builder()
                .title(title)
                .aim(aim)
                .purpose(purpose)
                .dueDate(dueDate).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({"test title, test aim, test purpose"})
    void goal_create_不正パラメータ_dueDate(String title, String aim, String purpose) throws Exception {
        var request = GoalCreateParam.builder()
                .title(title)
                .aim(aim)
                .purpose(purpose)
                .dueDate(LocalDate.now().minusDays(1)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_create_authorization() throws Exception {
        var request = GoalCreateParam.builder()
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());

    }

    @Test
    void goal_search_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/search")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_search_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/search")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_search_authorization_list_limit() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/search")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()))
                .param("month", StringUtils.nullSafeToString(LocalDate.now().getMonth().getValue()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        Type collectionType = new TypeToken<Collection<GoalSearchModel>>(){}.getType();
        List<GoalSearchModel> dto = new Gson().fromJson(resBodyAsString, collectionType);

        // 10件であること
        assertEquals(10, dto.size());

    }


    @Test
    void goal_detail_未認証() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


    @Test
    void goal_detail_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/detail")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


    @Test
    void goal_detail_authorization_目標なし() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "0")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


    @Test
    void goal_detail_authorization_パラメータなし() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON);

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


    @Test
    void goal_detail_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("goalId", "1")
                .param("createDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();

        var resBodyAsString = res.getResponse().getContentAsString();
        GoalSearchModel dto = new Gson().fromJson(resBodyAsString, GoalSearchModel.class);

        // goalIdは1のデータが返却される想定
        assertEquals(1L, dto.getId());

    }



    @Test
    void goal_update_不正_アカウント存在しない() throws Exception {
        var request = GoalCreateParam.builder()
                .goalId(1L)
                .createDate(LocalDate.now())
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_update_不正_アカウント種別不正() throws Exception {
        var request = GoalCreateParam.builder()
                .goalId(1L)
                .createDate(LocalDate.now())
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "1,, test aim, test purpose, 2022-09-30",
            "0,test title, test aim, test purpose, 2022-09-30"
    })
    void goal_update_不正パラメータ_title(Long goalId, String title, String aim, String purpose, LocalDate dueDate) throws Exception {
        var request = GoalCreateParam.builder()
                .goalId(goalId)
                .createDate(LocalDate.now())
                .title(title)
                .aim(aim)
                .purpose(purpose)
                .dueDate(dueDate).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_update_authorization() throws Exception {
        var request = GoalCreateParam.builder()
                .goalId(1L)
                .createDate(LocalDate.now())
                .title("test")
                .aim("test")
                .purpose("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());

    }

    @Test
    void goal_archive_create_アカウント存在しない() throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .goalId(1L)
                .createDate(LocalDate.now())
                .thoughts("test")
                .publish(PublishLevel.ALL).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create/archive")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_archive_create_アカウント種別不正() throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .goalId(1L)
                .createDate(LocalDate.now())
                .thoughts("test")
                .publish(PublishLevel.ALL).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create/archive")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "0,thoughts test, 0",
            "1,, 0",
            "1,thoughts test, Z",
    })
    void goal_archive_create_authorization_パラメータ不正(Long goalId, String thoughts, String published) throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .goalId(goalId)
                .createDate(LocalDate.now())
                .thoughts(thoughts)
                .publish(PublishLevel.of(published)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create/archive")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "1,thoughts test, 0",
    })
    void goal_archive_create_authorization_正常(Long goalId, String thoughts, String published) throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .goalId(goalId)
                .createDate(LocalDate.now())
                .thoughts(thoughts)
                .publish(PublishLevel.of(published)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/create/archive")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();

        var resBodyAsString = res.getResponse().getContentAsString();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        TGoalArchive dto = gson.fromJson(resBodyAsString, TGoalArchive.class);
        assertNotNull(dto);
    }


    @Test
    void goal_archive_update_archive_display_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/update/archive/display")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_archive_update_archive_display_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/update/archive/display")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_archive_update_archive_display_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/update/archive/display")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        TGoalArchive dto = gson.fromJson(resBodyAsString, TGoalArchive.class);
        assertNotNull(dto);

    }

    @Test
    void goal_archive_update_アカウント存在しない() throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .archiveId(1L)
                .archiveCreateDate(LocalDate.now())
                .goalId(1L)
                .createDate(LocalDate.now())
                .thoughts("update")
                .publish(PublishLevel.ALL).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_archive_update_アカウント種別不正() throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .archiveId(1L)
                .archiveCreateDate(LocalDate.now())
                .goalId(1L)
                .createDate(LocalDate.now())
                .thoughts("update")
                .publish(PublishLevel.ALL).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "0,thoughts test, 0",
            "1,, 0",
            "1,thoughts test, Z",
    })
    void goal_archive_update_authorization_パラメータ不正(Long archiveId, String thoughts, String published) throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .archiveId(archiveId)
                .archiveCreateDate(LocalDate.now())
                .thoughts(thoughts)
                .publish(PublishLevel.of(published)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "1,thoughts test, 0",
    })
    void goal_archive_update_authorization_正常(Long archiveId, String thoughts, String published) throws Exception {
        var request = GoalArchiveCreateParam.builder()
                .archiveId(archiveId)
                .archiveCreateDate(LocalDate.now())
                .thoughts(thoughts)
                .publish(PublishLevel.of(published)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();

        var resBodyAsString = res.getResponse().getContentAsString();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        TGoalArchive dto = gson.fromJson(resBodyAsString, TGoalArchive.class);
        assertNotNull(dto);
    }



    @Test
    void goal_archive_search_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/search")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_search_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/search")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_search_authorization_list_limit() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/search")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "10")
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()))
                .param("month", StringUtils.nullSafeToString(LocalDate.now().getMonth().getValue()));

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        Type collectionType = new TypeToken<Collection<GoalSearchModel>>(){}.getType();
        List<GoalSearchModel> dto = new Gson().fromJson(resBodyAsString, collectionType);

        // 10件であること
        assertEquals(10, dto.size());

    }



    @Test
    void goal_my_archive_search_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/list")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_my_archive_search_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/list")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()))
                .param("month", StringUtils.nullSafeToString(LocalDate.now().getMonth().getValue()));

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_my_archive_search_authorization_list_limit() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/list")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("year", StringUtils.nullSafeToString(LocalDate.now().getYear()))
                .param("month", StringUtils.nullSafeToString(LocalDate.now().getMonth().getValue()));

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
    void goal_archive_detail_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_detail_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_detail_authorization() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        GoalArchiveDetailResponse dto = new Gson().fromJson(resBodyAsString, GoalArchiveDetailResponse.class);
        assertNotNull(dto);


    }



    @Test
    void goal_my_archive_detail_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_my_archive_detail_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/detail")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_my_archive_detail_authorization() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/my-archive/detail")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        GoalArchiveDetailResponse dto = new Gson().fromJson(resBodyAsString, GoalArchiveDetailResponse.class);
        assertNotNull(dto);


    }



    @Test
    void goal_archive_edit_display_アカウント存在しない() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/edit-disp")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_edit_display_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/edit-disp")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    void goal_archive_edit_display_authorization() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/goal/archive/edit-disp")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .param("archiveId", "1")
                .param("archiveCreateDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
                ;

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();
        var resBodyAsString = res.getResponse().getContentAsString();
        GoalArchiveDetailResponse dto = new Gson().fromJson(resBodyAsString, GoalArchiveDetailResponse.class);
        assertNotNull(dto);


    }

    @Test
    void goal_archive_update_update_flg_アカウント存在しない() throws Exception {
        var request = GoalArchiveUpdatingFlgParameter.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive/updating-flg")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @Test
    void goal_archive_update_update_flg_アカウント種別不正() throws Exception {
        var request = GoalArchiveUpdatingFlgParameter.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive/updating-flg")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "0"
    })
    void goal_archive_update_update_flg_authorization_パラメータ不正(Long goalId) throws Exception {

        var request = GoalArchiveUpdatingFlgParameter.builder()
                .goalId(goalId)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive/updating-flg")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());

    }

    @ParameterizedTest
    @CsvSource({
            "1",
    })
    void goal_archive_update_update_flg_authorization_正常(Long goalId) throws Exception {

        var request = GoalArchiveUpdatingFlgParameter.builder()
                .goalId(goalId)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal/update/archive/updating-flg")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        var res = mockMvc.perform(build)
                .andExpect(status().isOk())
                .andReturn();

        var resBodyAsString = res.getResponse().getContentAsString();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        GoalSearchModel dto = gson.fromJson(resBodyAsString, GoalSearchModel.class);
        assertNotNull(dto);
    }
}
