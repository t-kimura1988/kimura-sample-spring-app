package daiku.app.service;

import daiku.app.DaikuApplication;
import daiku.app.service.input.goalFavorite.FavoriteCreateServiceInput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TGoalFavorites;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.repository.GoalFavoriteRepository;
import daiku.domain.repository.GoalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest(classes = DaikuApplication.class)
@AutoConfigureMockMvc
public class GoalFavoriteServiceTest {

    @Autowired
    @InjectMocks
    private GoalFavoriteService goalFavoriteService;

    @SpyBean
    private GoalRepository goalRepository;

    @SpyBean
    private GoalFavoriteRepository goalFavoriteRepository;

    @Test
    @DisplayName("目標情報が存在しなかった")
    void test1() {
        try {
            doReturn(Optional.empty()).when(goalRepository).detail(any());
            goalFavoriteService.create(FavoriteCreateServiceInput.builder()
                    .goalId(1L)
                    .accountId(1L)
                    .createDate(LocalDate.now()).build());
        }catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "goal detail info no found");
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @DisplayName("おすすめ目標情報がなかったら登録処理")
    void test2() {
        try {
            GoalSearchModel goalSearchModel = new GoalSearchModel();
            goalSearchModel.setId(1L);
            goalSearchModel.setAccountId(1L);
            goalSearchModel.setTitle("test");
            doReturn(Optional.of(goalSearchModel)).when(goalRepository).detail(any());

            doReturn(Optional.empty()).when(goalFavoriteRepository).selectByAccountIdAndGoalId(any());
            doNothing().when(goalFavoriteRepository).save(any());

            goalFavoriteService.create(FavoriteCreateServiceInput.builder()
                    .goalId(1L)
                    .accountId(1L)
                    .createDate(LocalDate.now()).build());

            verify(goalFavoriteRepository).save(any());

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @DisplayName("おすすめ目標情報があったら削除処理")
    void test3() {
        try {
            GoalSearchModel goalSearchModel = new GoalSearchModel();
            goalSearchModel.setId(1L);
            goalSearchModel.setAccountId(1L);
            goalSearchModel.setTitle("test");
            doReturn(Optional.of(goalSearchModel)).when(goalRepository).detail(any());
            TGoalFavorites goalFavorites = new TGoalFavorites();
            goalFavorites.setId(1L);
            goalFavorites.setGoalId(1L);
            doReturn(Optional.of(goalFavorites)).when(goalFavoriteRepository).selectByAccountIdAndGoalId(any());
            doNothing().when(goalFavoriteRepository).delete(any());

            goalFavoriteService.create(FavoriteCreateServiceInput.builder()
                    .goalId(1L)
                    .accountId(1L)
                    .createDate(LocalDate.now()).build());

            verify(goalFavoriteRepository).delete(any());

        } catch (Exception e) {
            fail();
        }

    }
}
