package daiku.app.service;

import daiku.app.service.input.goal.*;
import daiku.app.service.output.goal.*;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TGoalArchive;
import daiku.domain.entity.TGoals;
import daiku.domain.entity.TMakiGoalRelation;
import daiku.domain.enums.PublishLevel;
import daiku.domain.enums.UpdatingFlg;
import daiku.domain.model.param.GoalArchiveDaoParam;
import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.param.ProcessDaoParam;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.model.res.ProcessSearchModel;
import daiku.domain.repository.GoalArchiveRepository;
import daiku.domain.repository.GoalRepository;
import daiku.domain.repository.MakiGoalRepository;
import daiku.domain.repository.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    @Autowired
    GoalArchiveRepository goalArchiveRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    MakiGoalRepository makiGoalRepository;

    public GoalSearchServiceOutput search(GoalSearchServiceInput input) {
        var goalList = goalRepository.search(input.toRepository());
        return GoalSearchServiceOutput.builder()
                .goalList(goalList).build();
    }

    public GoalArchiveSearchServiceOutput archiveSearch(GoalArchiveSearchServiceInput input) {
        var goalList = goalArchiveRepository.archiveSearch(input.toRepository());
        return GoalArchiveSearchServiceOutput.builder()
                .goalList(goalList).build();
    }

    public MyGoalArchiveSearchServiceOutput myArchiveSearch(MyGoalArchiveSearchServiceInput input) {
        var goalList = goalArchiveRepository.myArchiveSearch(input.toRepository());
        return MyGoalArchiveSearchServiceOutput.builder()
                .goalList(goalList).build();
    }

    public GoalArchiveDetailServiceOutput archiveDetail(GoalArchiveDetailServiceInput input) throws GoenNotFoundException {
        var goalArchive = goalArchiveRepository.archiveOptional(input.toArchiveRepository())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Archive.id: ", input.getArchiveId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        });

        var goalInfo = new GoalSearchModel();
        List<ProcessSearchModel> processInfo = null;

        goalInfo = goalRepository.detail(GoalDaoParam.builder()
                .goalId(goalArchive.getGoalId())
                .createDate(goalArchive.getGoalCreateDate()).build())
                .orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("goal.id: ", input.getArchiveId().toString());
                    return new GoenNotFoundException("goal detail info no found", param);
                });

        if(goalArchive.getPublish().equals(PublishLevel.ALL)) {
            processInfo = processRepository.search(ProcessDaoParam.builder()
                    .createDate(goalInfo.getCreateDate())
                    .goalId(goalInfo.getId()).build());
        }
        return GoalArchiveDetailServiceOutput.builder()
                .goalArchiveInfo(goalArchive)
                .goalInfo(goalInfo)
                .processInfo(processInfo).build();
    }

    public MyGoalArchiveDetailServiceOutput myArchiveDetail(MyGoalArchiveDetailServiceInput input) throws GoenNotFoundException {
        var goalArchive = goalArchiveRepository.myArchiveOptional(input.toArchiveRepository())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Archive.id: ", input.getArchiveId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        });
        return MyGoalArchiveDetailServiceOutput.builder()
                .goalArchiveInfo(goalArchive)
                .goalInfo(goalRepository.detail(GoalDaoParam.builder()
                                .goalId(goalArchive.getGoalId())
                                .createDate(goalArchive.getGoalCreateDate()).build())
                        .orElseThrow(
                                () -> {
                                    Map<String, String> param = new LinkedHashMap<>();
                                    param.put("goal.id: ", input.getArchiveId().toString());
                                    return new GoenNotFoundException("goal detail info no found", param);
                                }))
                .processInfo( processRepository.search(ProcessDaoParam.builder()
                        .createDate(goalArchive.getGoalCreateDate())
                        .goalId(goalArchive.getGoalId()).build())).build();
    }

    public GoalArchiveEditDisplayServiceOutput getArchiveEdit(GoalArchiveEditDisplayServiceInput input) throws GoenNotFoundException{
        var goalArchive = goalArchiveRepository.myArchiveOptional(input.toArchiveRepository())
                .orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Archive.id: ", input.getArchiveId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        });
        return GoalArchiveEditDisplayServiceOutput.builder()
                .goalArchiveInfo(goalArchive)
                .build();
    }

    public GoalArchiveUpdateDispServiceOutput archiveUpdateDisp(GoalArchiveUpdateDispServiceInput input) throws GoenNotFoundException {
        return GoalArchiveUpdateDispServiceOutput.builder()
                .goalArchiveInfo(
                        goalArchiveRepository.selectById(input.toArchiveRepository())
                                .orElseThrow(
                                        () -> {
                                            Map<String, String> param = new LinkedHashMap<>();
                                            param.put("archive.id: ", input.getArchiveId().toString());
                                            return new GoenNotFoundException("archive info no found", param);

                                        }
                                )
                )
                .build();
    }

    public GoalSearchModel create(GoalCreateServiceInput input) throws GoenNotFoundException{
        TGoals goals = input.toEntity();
        goalRepository.save(goals);

        if(input.getMakiId() != null && input.getMakiId() != 0L) {
            var maxNum = makiGoalRepository.makiGoalRelationSortNumMax(input.getMakiId());

            if(maxNum == null) {
                maxNum = 1L;
            } else {
                maxNum++;
            }
            TMakiGoalRelation makiGoal = new TMakiGoalRelation();
            makiGoal.setGoalId(goals.getId());
            makiGoal.setMakiId(input.getMakiId());
            makiGoal.setSortNum(maxNum);
            makiGoal.setGoalCreateDate(goals.getCreateDate());
            makiGoalRepository.save(makiGoal);
        }

        return goalRepository.detail(GoalDaoParam.builder()
                .goalId(goals.getId())
                .accountId(input.getAccountId())
                .createDate(goals.getCreateDate()).build()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id: ", goals.getId().toString());
                    return new GoenNotFoundException("goal detail info no found", param);
                }
        );
    }

    public GoalSearchModel update(GoalUpdateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(GoalDaoParam.builder()
                .goalId(input.getGoalId())
                .createDate(input.getCreateDate())
                .build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Goal.id: ", input.getGoalId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        }
                );
        goalRepository.save(input.toEntity());

        return goalRepository.detail(GoalDaoParam.builder()
                .goalId(input.getGoalId())
                .accountId(input.getAccountId())
                .createDate(input.getCreateDate()).build()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id: ", input.getGoalId().toString());
                    return new GoenNotFoundException("goal detail info no found", param);
                }
        );
    }

    public GoalDetailServiceOutput detail(GoalDetailServiceInput input) throws GoenNotFoundException {

        return GoalDetailServiceOutput.builder()
                .goal(goalRepository.detail(input.toParam()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Goal.id: ", input.getGoalId().toString());
                            return new GoenNotFoundException("goal detail info no found", param);
                        }
                ))
                .build();
    }

    public TGoalArchive archive(GoalArchiveServiceInput input) throws GoenNotFoundException, GoenIntegrityException {
        var goal = goalRepository.detail(
                        GoalDaoParam.builder()
                                .goalId(input.getGoalId())
                                .createDate(input.getCreateDate())
                                .accountId(input.getAccountId()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("goal info not found exception", param, "");
                });
        AtomicReference<TGoalArchive> archive = new AtomicReference<>(new TGoalArchive());
        archive.set(input.toEntity());
        goalArchiveRepository.selectByGoalId(goal.getId()).ifPresentOrElse(
                goalArchive -> {
                    goalArchive.setThoughts(input.getThoughts());
                    goalArchive.setPublish(input.getPublish());
                    goalArchive.setUpdatingFlg(UpdatingFlg.ARCHIVING);
                    goalArchiveRepository.save(goalArchive);
                    archive.set(goalArchive);
                },
                () -> {
                    goalArchiveRepository.save(archive.get());
                });


        return goalArchiveRepository.selectById(
                GoalArchiveDaoParam.builder()
                        .archiveId(archive.get().getId())
                        .archiveCreateDate(archive.get().getArchivesCreateDate())
                        .build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Archive.id", archive.get().getId().toString());
                    return new GoenNotFoundException("save archive info not found exception", param, "");
                });

    }

    public TGoalArchive archiveUpdate(GoalArchiveUpdateServiceInput input) throws GoenNotFoundException {
        var archive = goalArchiveRepository.selectById(input.toArchiveRepositoryParam())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("archive.id", input.getArchiveId().toString());
                    return new GoenNotFoundException("archive info not found exception", param, "");
                });

        goalArchiveRepository.save(input.toUpdateEntity(archive));

        return archive;

    }

    public GoalSearchModel archiveUpdatingFlg(GoalArchiveUpdatingFlgServiceInput input) throws GoenNotFoundException {
        var archive = goalArchiveRepository.selectByGoalId(input.getGoalId())
                .filter(data -> data.getUpdatingFlg().equals(UpdatingFlg.ARCHIVING))
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("goal.id", input.getGoalId().toString());
                    return new GoenNotFoundException("archive info not found exception", param, "");
                });
        goalArchiveRepository.save(input.toEntity(archive));

        return goalRepository.detail(GoalDaoParam.builder()
                .goalId(archive.getGoalId())
                .accountId(input.getAccountId())
                .createDate(input.getGoalCreateDate()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("archive.id", archive.getId().toString());
                    return new GoenNotFoundException("goal not found exception", param, "");
                });

    }
}
