package daiku.domain.repository;

import daiku.domain.dao.StepDao;
import daiku.domain.entity.TSteps;
import daiku.domain.model.param.StepDaoParam;
import daiku.domain.model.res.StepSearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class StepRepository {
    @Autowired
    StepDao stepDao;

    public List<StepSearchModel> selectList(StepDaoParam param) {
        return stepDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<StepSearchModel> selectOptional(StepDaoParam param) {
        return stepDao.select(param, SelectOptions.get(), toOptional());
    }

    public void save(TSteps steps) {
        Optional.ofNullable(steps.getId())
                .ifPresentOrElse(
                        id -> stepDao.update(steps),
                        () -> stepDao.insert(steps)
                );
    }


}
