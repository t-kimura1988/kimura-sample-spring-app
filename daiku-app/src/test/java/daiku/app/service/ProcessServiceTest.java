package daiku.app.service;

import daiku.app.DaikuApplication;
import daiku.app.service.input.process.ProcessCreateServiceInput;
import daiku.app.service.input.process.ProcessUpdateServiceInput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.enums.ProcessPriority;
import daiku.domain.enums.ProcessStatus;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.model.res.ProcessHistorySearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import daiku.domain.repository.GoalRepository;
import daiku.domain.repository.ProcessHistoryRepository;
import daiku.domain.repository.ProcessRepository;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest(classes = DaikuApplication.class)
@AutoConfigureMockMvc
public class ProcessServiceTest {

    @Autowired
    @InjectMocks
    private ProcessService processService;

    @SpyBean
    private ProcessRepository processRepository;

    @SpyBean
    private GoalRepository goalRepository;

    @SpyBean
    private ProcessHistoryRepository processHistoryRepository;

    @Test
    @DisplayName("目標情報が存在しなかった")
    void create_test1() {
        try {
            doReturn(Optional.empty()).when(goalRepository).detail(any());
            processService.create(ProcessCreateServiceInput.builder()
                    .goalId(1L)
                    .accountId(1L)
                    .title("Goal Title")
                    .body("Goal Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        }catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "goal info not found exception");
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @DisplayName("正常にプロセスが登録できる")
    void create_test2() {

        try {
            GoalSearchModel model = new GoalSearchModel();
            model.setId(1L);
            model.setTitle("Goal Title");
            model.setAim("Goal Aim");
            model.setCreateDate(LocalDate.now());
            model.setAccountId(1L);

            doReturn(Optional.of(model)).when(goalRepository).detail(any());
            doNothing().when(processRepository).save(any());
            doNothing().when(processHistoryRepository).save(any());

            processService.create(ProcessCreateServiceInput.builder()
                    .goalId(1L)
                    .accountId(1L)
                    .title("Process Title")
                    .body("Process Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("目標が存在しない")
    void update_test1() {

        try {
            doReturn(Optional.empty()).when(goalRepository).detail(any());
            processService.update(ProcessUpdateServiceInput.builder()
                    .processId(1L)
                    .goalId(1L)
                    .accountId(1L)
                    .goalCreateDate(LocalDate.now())
                    .title("Process Title")
                    .body("Process Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        }catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "goal info not found exception");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("プロセスヒストリーに最新データがない")
    void update_test2() {

        try {
            GoalSearchModel model = new GoalSearchModel();
            model.setId(1L);
            model.setTitle("Goal Title");
            model.setAim("Goal Aim");
            model.setCreateDate(LocalDate.now());
            model.setAccountId(1L);

            doReturn(Optional.of(model)).when(goalRepository).detail(any());

            doReturn(Optional.empty()).when(processHistoryRepository).selectForLatest(any());

            processService.update(ProcessUpdateServiceInput.builder()
                    .processId(1L)
                    .goalId(1L)
                    .accountId(1L)
                    .goalCreateDate(LocalDate.now())
                    .title("Process Title")
                    .body("Process Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        }catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "process-history latest info not found");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("目標が存在しない")
    void update_test3() {

        try {
            GoalSearchModel model = new GoalSearchModel();
            model.setId(1L);
            model.setTitle("Goal Title");
            model.setAim("Goal Aim");
            model.setCreateDate(LocalDate.now());
            model.setAccountId(1L);

            doReturn(Optional.of(model)).when(goalRepository).detail(any());

            ProcessHistorySearchModel phModel = new ProcessHistorySearchModel();
            phModel.setId(1L);
            phModel.setAccountId(1L);
            phModel.setBeforePriority(ProcessPriority.LOW);
            phModel.setPriority(ProcessPriority.HEIGHT);
            phModel.setBeforeProcessStatus(ProcessStatus.NEW);
            phModel.setProcessStatus(ProcessStatus.PROBLEM);
            doReturn(Optional.of(phModel)).when(processHistoryRepository).selectForLatest(any());

            doReturn(Optional.empty()).when(processRepository).detail(any());

            processService.update(ProcessUpdateServiceInput.builder()
                    .processId(1L)
                    .goalId(1L)
                    .accountId(1L)
                    .goalCreateDate(LocalDate.now())
                    .title("Process Title")
                    .body("Process Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        }catch(GoenNotFoundException e) {
            assertEquals(e.getMessage(), "process detail info not found");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("正常")
    void update_test4() {

        try {
            GoalSearchModel model = new GoalSearchModel();
            model.setId(1L);
            model.setTitle("Goal Title");
            model.setAim("Goal Aim");
            model.setCreateDate(LocalDate.now());
            model.setAccountId(1L);

            doReturn(Optional.of(model)).when(goalRepository).detail(any());

            ProcessHistorySearchModel phModel = new ProcessHistorySearchModel();
            phModel.setId(1L);
            phModel.setAccountId(1L);
            phModel.setBeforePriority(ProcessPriority.LOW);
            phModel.setPriority(ProcessPriority.HEIGHT);
            phModel.setBeforeProcessStatus(ProcessStatus.NEW);
            phModel.setProcessStatus(ProcessStatus.PROBLEM);
            doReturn(Optional.of(phModel)).when(processHistoryRepository).selectForLatest(any());

            ProcessSearchModel processSearchModel = new ProcessSearchModel();
            processSearchModel.setId(1L);
            processSearchModel.setGoalId(1L);
            processSearchModel.setAccountId(1L);
            doReturn(Optional.of(processSearchModel)).when(processRepository).detail(any());

            doNothing().when(processHistoryRepository).save(any());
            doNothing().when(processRepository).save(any());

            processService.update(ProcessUpdateServiceInput.builder()
                    .processId(1L)
                    .goalId(1L)
                    .accountId(1L)
                    .goalCreateDate(LocalDate.now())
                    .title("Process Title")
                    .body("Process Body")
                    .processPriority(ProcessPriority.LOW)
                    .processStatus(ProcessStatus.NEW).build());
        } catch (Exception e) {
            fail();
        }
    }
}
