package daiku.app.service;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import daiku.app.DaikuApplication;
import daiku.app.service.input.processHistory.ProcessHistoryDetailServiceInput;
import daiku.app.service.input.processHistory.ProcessHistorySearchServiceInput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.repository.GoalRepository;
import daiku.domain.repository.ProcessHistoryRepository;
import daiku.domain.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest(classes = DaikuApplication.class)
public class ProcessHistoryServiceTest {

    @Autowired
    private ProcessHistoryService processHistoryService;

    @SpyBean
    private ProcessRepository processRepository;

    @SpyBean
    private GoalRepository goalRepository;

    @SpyBean
    private ProcessHistoryRepository processHistoryRepository;

    @Mock
    private Appender<ILoggingEvent> mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("工程履歴一覧で工程情報が存在しなかった")
    void search_test1() {
        Long processId = 1L;
        doReturn(Optional.empty()).when(processRepository).detail(any());

        var error = assertThrows(GoenNotFoundException.class, () -> {
            processHistoryService.search(ProcessHistorySearchServiceInput.builder()
                    .processId(processId)
                    .accountId(1L)
                    .build());
        });
        assertEquals("process detail info no found", error.getMessage());
        assertEquals(processId.toString(), error.getDetail().get("Process.id"));
    }

    @Test
    @DisplayName("正常取得_工程履歴(ID:14)が４件取得できること")
    void search_test2() throws GoenNotFoundException {
        var output = processHistoryService.search(ProcessHistorySearchServiceInput.builder()
                .processId(14L)
                .accountId(1L)
                .build());

        assertEquals(4, output.getProcessHistorySearchModelList().size());
    }
    @Test
    @DisplayName("工程履歴詳細が存在しなかった")
    void detail_test1() {
        Long processId = 1L;
        doReturn(Optional.empty()).when(processHistoryRepository).detail(any());

        var error = assertThrows(GoenNotFoundException.class, () -> {
            processHistoryService.detail(ProcessHistoryDetailServiceInput.builder()
                    .processHistoryId(1L)
                    .accountId(1L)
                    .goalCreateDate(LocalDate.now())
                    .build());
        });
        assertEquals("process history detail info not found", error.getMessage());
        assertEquals(processId.toString(), error.getDetail().get("ProcessHistory.id"));
    }

    @Test
    @DisplayName("正常_工程履歴詳細レスポンスが正常")
    void detail_test2() throws GoenNotFoundException {

        var output = processHistoryService.detail(ProcessHistoryDetailServiceInput.builder()
                .processHistoryId(14L)
                .accountId(1L)
                .goalCreateDate(LocalDate.now())
                .build());

        assertEquals(14L, output.getProcessHistoryDetail().getId());

    }



}
