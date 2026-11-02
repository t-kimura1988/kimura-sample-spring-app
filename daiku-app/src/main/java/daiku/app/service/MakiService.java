package daiku.app.service;

import daiku.app.service.input.maki.*;
import daiku.app.service.output.maki.*;
import daiku.domain.exception.GoenBadRequestException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.TMakiGoalRelation;
import daiku.domain.entity.TMakis;
import daiku.domain.model.param.AddGoalListParam;
import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.param.MakiDaoParam;
import daiku.domain.model.param.MakiGoalDaoParam;
import daiku.domain.model.res.MakiSearchModel;
import daiku.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MakiService {
    @Autowired
    MakiRepository makiRepository;

    @Autowired
    GoalArchiveRepository goalArchiveRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    MakiGoalRepository makiGoalRepository;

    public MakiSearchModel create(MakiCreateServiceInput input) throws GoenNotFoundException{
        TMakis makis = input.toEntity();
        makiRepository.save(makis);

        return makiRepository.detail(MakiDaoParam.builder()
                .makiId(makis.getId())
                .accountId(input.getAccountId()).build()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", makis.getId().toString());
                    return new GoenNotFoundException("maki detail info no found", param);
                }
        );
    }

    public MakiSearchServiceOutput search(MakiSearchServiceInput input) {
        return MakiSearchServiceOutput.builder()
                .makiList(makiRepository.list(input.toRepository())).build();
    }

    public MakiGoalListServiceOutput goalList(MakiGoalListServiceInput input) throws GoenNotFoundException {
        var maki = makiRepository.detail(input.toMakiDetailParam()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", input.getMakiId().toString());
                    return new GoenNotFoundException("maki detail info no found", param);
                });
        var goalList = makiRepository.makiGoalList(MakiDaoParam.builder()
                .makiId(maki.getId())
                .accountId(maki.getAccountId()).build());
        return MakiGoalListServiceOutput.builder()
                .makiGoalList(goalList).build();
    }

    public MakiAddGoalListServiceOutput addGoalList(MakiAddGoalListServiceInput input) throws GoenNotFoundException {
        var maki = makiRepository.detail(input.toMakiDetailParam()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", input.getMakiId().toString());
                    return new GoenNotFoundException("maki detail info no found", param);
                });
        var goalList = goalRepository.addGoalList(AddGoalListParam.builder()
                .makiId(maki.getId())
                .accountId(maki.getAccountId()).build());
        return MakiAddGoalListServiceOutput.builder()
                .list(goalList).build();
    }

    public MakiDetailServiceOutput detail(MakiDetailServiceInput input) throws GoenNotFoundException, GoenBadRequestException {
        var maki = makiRepository.detail(input.toRepository())
                .orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", input.getMakiId().toString());
                    return new GoenNotFoundException("maki detail info no found", param, "E0007");
                }
        );

        if (!input.getAccountId().equals(maki.getAccountId())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Maki.account_id: ", maki.getAccountId().toString());
            param.put("Login Account.id: ", input.getAccountId().toString());
            throw new GoenBadRequestException("", param);
        }

        return MakiDetailServiceOutput.builder()
                .maki(maki).build();
    }

    public MakiAddGoalServiceOutput makiAddGoal(MakiAddGoalServiceInput input){
        List<TMakiGoalRelation> res = new ArrayList<TMakiGoalRelation>();
        AtomicReference<Long> num = new AtomicReference<>(1L);
        input.getBody().stream().forEach(item -> {
            try {
                if (item.getGoalId() == 0 || item.getGoalCreateDate() == null || item.getMakiId() == 0) {
                    return;
                }
                Long maxNum = makiGoalRepository.makiGoalRelationSortNumMax(item.getMakiId());
                Long addNum = 0L;
                if(maxNum == null) {
                    addNum++;
                } else {
                    addNum = makiGoalRepository.makiGoalRelationSortNumMax(item.getMakiId()) + num.get();
                }
                goalRepository.detail(GoalDaoParam.builder()
                        .goalId(item.getGoalId())
                        .createDate(item.getGoalCreateDate()).build()).orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", item.getMakiId().toString());
                    return new GoenNotFoundException("maki detail info no found", param);
                });
                var isPresent = makiGoalRepository.makiGoalOptional(MakiGoalDaoParam.builder()
                        .goalId(item.getGoalId())
                        .goalCreateDate(item.getGoalCreateDate()).build())
                        .isPresent();

                if (isPresent) {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Maki.id: ", item.getMakiId().toString());
                    throw new GoenNotFoundException("maki goal relation is already exit:", param);
                }

                var entity = item.toEntity(addNum);

                makiGoalRepository.save(entity);

                res.add(entity);

            } catch (GoenNotFoundException e) {
                log.error(e.getMessage());
            }
        });

        return MakiAddGoalServiceOutput.builder()
                .list(res).build();
    }
}
