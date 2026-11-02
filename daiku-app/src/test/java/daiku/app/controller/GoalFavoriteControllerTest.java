package daiku.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import daiku.app.controller.input.goalFavorite.GoalFavoriteCreateParam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoalFavoriteControllerTest extends ControllerBaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void goal_favorite_change_アカウント存在なし() throws Exception {
        var request = GoalFavoriteCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal-favorite/change")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void goal_favorite_change_アカウント種別不正() throws Exception {
        var request = GoalFavoriteCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal-favorite/change")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void goal_favorite_change_アカウント_firebase_delete() throws Exception {
        var request = GoalFavoriteCreateParam.builder()
                .goalId(1L)
                .goalCreateDate(LocalDate.now()).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal-favorite/change")
                .with(firebaseDelAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 2099-09-10",
            "1,",
            ",2099-09-10"
    })
    void goal_favorite_change_authorization_パラメータ不正(Long goalId, LocalDate goalCreateDate) throws Exception {
        var request = GoalFavoriteCreateParam.builder()
                .goalId(goalId)
                .goalCreateDate(goalCreateDate).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/goal-favorite/change")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

}
