package daiku.app.service;

import daiku.app.service.input.processHistory.*;
import daiku.app.service.output.processHistory.ProcessHistoryDetailServiceOutput;
import daiku.app.service.output.processHistory.ProcessHistorySearchServiceOutput;
import daiku.domain.entity.TProcessesHistory;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.param.ProcessHistoryDaoParam;
import daiku.domain.model.param.ProcessHistoryDuringProcessParam;
import daiku.domain.model.res.ProcessHistoryDuringProcessModel;
import daiku.domain.model.res.ProcessHistorySearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import daiku.domain.repository.ProcessHistoryRepository;
import daiku.domain.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessHistoryService {

    @Autowired
    ProcessHistoryRepository processHistoryRepository;

    @Autowired
    ProcessRepository processRepository;

    public ProcessHistorySearchServiceOutput search(ProcessHistorySearchServiceInput input) throws GoenNotFoundException {
        ProcessSearchModel detail = processRepository.detail(input.toProcessDetailParam())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Process.id", input.getProcessId().toString());
                    return new GoenNotFoundException("process detail info no found", param);
                });

        return ProcessHistorySearchServiceOutput.builder()
                .processHistorySearchModelList(processHistoryRepository.list(ProcessHistoryDaoParam.builder()
                        .processId(detail.getId())
                        .accountId(detail.getAccountId())
                        .goalCreateDate(detail.getGoalCreateDate()).build())).build();
    }

    public ProcessHistoryDetailServiceOutput detail(ProcessHistoryDetailServiceInput input) throws GoenNotFoundException {
        return ProcessHistoryDetailServiceOutput.builder()
                .processHistoryDetail(
                        processHistoryRepository.detail(input.toProcessDetailParam()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("ProcessHistory.id", input.getProcessHistoryId().toString());
                                    return new GoenNotFoundException("process history detail info not found", param);
                                }
                        )
                )
                .build();
    }

    public ProcessHistorySearchModel create(ProcessHistoryCreateServiceInput input) throws GoenNotFoundException {
        var process = processRepository.detail(input.toDetailParam()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Process.id: ", input.getProcessId().toString());
            return new GoenNotFoundException("process detail info not found", param);
        });


        TProcessesHistory updateProcessesHistory = input.toProcessDetailParam(
                processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                        .processId(input.getProcessId())
                        .goalCreateDate(process.getGoalCreateDate())
                        .accountId(input.getAccountId())
                        .latestFlg(true)
                        .build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Process.id: ", input.getProcessId().toString());
                            return new GoenNotFoundException("process-history latest info not found", param);
                        }
                )
        );

        processHistoryRepository.save(updateProcessesHistory);

        return processHistoryRepository.detail(
                        ProcessHistoryDaoParam.builder()
                                .id(updateProcessesHistory.getId())
                                .accountId(input.getAccountId())
                                .build()
                )
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("ProcessHistory.id: ", updateProcessesHistory.getId().toString());
                    return new GoenNotFoundException("process history detail info not found", param);
                });
    }

    public ProcessHistorySearchModel updateComment(ProcessHistoryUpdateCommentServiceInput input) throws GoenNotFoundException{
        processHistoryRepository.save(input.toProcessHistoryEntity());

        return processHistoryRepository.detail(
                ProcessHistoryDaoParam.builder()
                        .id(input.getProcessHistory())
                        .accountId(input.getAccountId())
                        .build()
                )
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("ProcessHistory.id: ", input.getProcessHistory().toString());
                    return new GoenNotFoundException("process history detail info not found", param);
                });
    }

    public ProcessHistorySearchModel updateStatus(ProcessHistoryUpdateStatusServiceInput input) throws GoenNotFoundException {
        var process = processRepository.detail(input.toDetailParam()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Process.id: ", input.getProcessId().toString());
            return new GoenNotFoundException("process detail info not found", param);
        });

        TProcessesHistory updateProcessesHistory = input.toProcessDetailParam(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(process.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        )
                );

        processHistoryRepository.save(updateProcessesHistory);


        return processHistoryRepository.detail(
                        ProcessHistoryDaoParam.builder()
                                .id(updateProcessesHistory.getId())
                                .accountId(input.getAccountId())
                                .build()
                )
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("ProcessHistory.id: ", updateProcessesHistory.getId().toString());
                    return new GoenNotFoundException("process history detail info not found", param);
                });
    }

    public List<ProcessHistoryDuringProcessModel> getScheduleList(ProcessHistoryScheduleListServiceInput input) {
        return processHistoryRepository.selectDuringProcess(ProcessHistoryDuringProcessParam.builder()
                .processHistoryDate(input.getProcessHistoryDate())
                .accountId(input.getAccountId()).build());
    }
}
