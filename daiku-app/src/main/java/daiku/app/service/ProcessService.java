package daiku.app.service;

import daiku.app.service.input.process.*;
import daiku.app.service.output.process.ProcessDetailServiceOutput;
import daiku.app.service.output.process.ProcessSearchServiceOutput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TProcesses;
import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.param.ProcessDaoParam;
import daiku.domain.model.param.ProcessHistoryDaoParam;
import daiku.domain.model.res.ProcessSearchModel;
import daiku.domain.repository.GoalRepository;
import daiku.domain.repository.ProcessHistoryRepository;
import daiku.domain.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessService {

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    ProcessHistoryRepository processHistoryRepository;

    public TProcesses create(ProcessCreateServiceInput input) throws GoenNotFoundException {
        var goal = goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .accountId(input.getAccountId()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("goal info not found exception", param);
                });

        TProcesses processes = input.toProcessEntity(goal.getCreateDate());

        processRepository.save(processes);
        processHistoryRepository.save(input.toProcessHistoryEntity(processes));

        return processes;
    }

    public TProcesses update(ProcessUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .createDate(input.getGoalCreateDate())
                        .accountId(input.getAccountId()).build()
        ).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Goal.id", input.getGoalId().toString());
            return new GoenNotFoundException("goal info not found exception", param);
        });

        processHistoryRepository.save(
                input.toProcessUpdateHistory(
                        processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                                .processId(input.getProcessId())
                                .goalCreateDate(input.getGoalCreateDate())
                                .accountId(input.getAccountId())
                                .latestFlg(true)
                                .build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process-history latest info not found", param);
                                }
                        ),
                        processRepository.detail(ProcessDaoParam.builder()
                                .id(input.getProcessId())
                                .createDate(input.getGoalCreateDate()).build()).orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("Process.id: ", input.getProcessId().toString());
                                    return new GoenNotFoundException("process detail info not found", param);
                                })
                )
        );

        processRepository.save(input.toProcessEntity());

        return input.toProcessEntity();

    }

    public ProcessSearchModel updateProcessDate(ProcessDateUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(
                GoalDaoParam.builder()
                        .goalId(input.getGoalId())
                        .createDate(input.getGoalCreateDate())
                        .accountId(input.getAccountId()).build()
        ).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Goal.id", input.getGoalId().toString());
            return new GoenNotFoundException("goal info not found exception", param);
        });

        var processDetail = processRepository.detail(ProcessDaoParam.builder()
                        .id(input.getProcessId())
                        .createDate(input.getGoalCreateDate()).build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Process.id: ", input.getProcessId().toString());
                            return new GoenNotFoundException("process-history latest info not found", param);
                        });

        var processHistory = processHistoryRepository.selectForLatest(ProcessHistoryDaoParam.builder()
                .processId(input.getProcessId())
                .goalCreateDate(input.getGoalCreateDate())
                .accountId(input.getAccountId())
                .latestFlg(true)
                .build()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Process.id: ", input.getProcessId().toString());
                    return new GoenNotFoundException("process-history latest info not found", param);
                }
        );

        var updateEntity = input.toProcessUpdateHistory(processHistory, processDetail);

        processHistoryRepository.save(updateEntity);

        return processDetail;

    }

    public ProcessSearchServiceOutput search(ProcessSearchServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(input.toGoalDaoParam())
                .orElseThrow(() -> {
                        Map<String, String> param = new LinkedHashMap<>();
                        param.put("Goal.id", input.getGoalId().toString());
                        return new GoenNotFoundException("goal info not found exception", param);
                });

        return ProcessSearchServiceOutput.builder()
                .processSearchModelList(processRepository.search(input.toProcessDaoParam()))
                .build();
    }

    public ProcessDetailServiceOutput detail(ProcessDetailServiceInput input) throws GoenNotFoundException{
        var detail = processRepository.detail(input.toParameter())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("process.id", input.getProcessId().toString());
                    return new GoenNotFoundException("goal info not found exception", param);
                });
        return ProcessDetailServiceOutput.builder()
                .process(detail).build();
    }
}
