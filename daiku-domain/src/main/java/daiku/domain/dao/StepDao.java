package daiku.domain.dao;

import daiku.domain.entity.TSteps;
import daiku.domain.model.param.StepDaoParam;
import daiku.domain.model.res.StepSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface StepDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(StepDaoParam param, SelectOptions options, Collector<StepSearchModel, ?, R> collector);

    @Insert
    int insert(TSteps stories);

    @Update(excludeNull = true)
    int update(TSteps stories);
}
