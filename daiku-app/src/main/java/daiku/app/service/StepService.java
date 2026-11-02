package daiku.app.service;

import daiku.app.service.input.step.StepCreateServiceInput;
import daiku.app.service.input.step.StepDateDetailServiceInput;
import daiku.app.service.input.step.StepUpdateServiceInput;
import daiku.app.service.output.step.StepCreateServiceOutput;
import daiku.app.service.output.step.StepDateDetailServiceOutput;
import daiku.app.service.output.step.StepUpdateServiceOutput;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.param.StepDaoParam;
import daiku.domain.repository.StepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StepService {
    @Autowired
    StepRepository stepRepository;

    public StepCreateServiceOutput create(StepCreateServiceInput input) throws GoenNotFoundException, GoenIntegrityException {
        var entity = input.toEntity();
        var data = stepRepository.selectOptional(StepDaoParam.builder()
                .createDate(input.getCreateDate()).build());
        if (data.isPresent()) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Step.id: ", data.get().getId().toString());
            param.put("date: ", data.get().getCreateDate().toString());
            throw new GoenIntegrityException("Step is already existed in date", param, "");
        }
        stepRepository.save(entity);

        return StepCreateServiceOutput.builder()
                .stepSearchModel(stepRepository.selectOptional(StepDaoParam.builder()
                        .stepId(entity.getId())
                        .createDate(entity.getCreateDate())
                        .accountId(entity.getAccountId()).build()).orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Step.id: ", entity.getId().toString());
                    return new GoenNotFoundException("step detail info no found", param);
                }))
                .build();
    }

    public StepUpdateServiceOutput update(StepUpdateServiceInput input) throws GoenNotFoundException {
        stepRepository.selectOptional(StepDaoParam.builder()
                .stepId(input.getStepId())
                .createDate(input.getCreateDate()).build())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Step Create Date: ", input.getCreateDate().toString());
                    param.put("Step ID: ", input.getStepId().toString());
                    return new GoenNotFoundException("step detail info no found", param);
                });

        stepRepository.save(input.toEntity());
        return StepUpdateServiceOutput.builder()
                .stepSearchModel(stepRepository.selectOptional(StepDaoParam.builder()
                                .stepId(input.getStepId())
                                .createDate(input.getCreateDate()).build())
                        .orElseThrow(() -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Step Updating Date: ", input.getCreateDate().toString());
                            param.put("Step ID: ", input.getStepId().toString());
                            return new GoenNotFoundException("step detail info no found", param);
                        })).build();
    }

    public StepDateDetailServiceOutput detail(StepDateDetailServiceInput input) throws GoenNotFoundException {
        var res = stepRepository.selectOptional(input.toParam())
                .orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Step Create Date: ", input.getCreateDate().toString());
                    return new GoenNotFoundException("step detail in target date info no found", param);
                });

        return StepDateDetailServiceOutput.builder()
                .stepSearchModel(res).build();
    }
}
