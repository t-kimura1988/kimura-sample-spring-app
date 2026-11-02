package daiku.domain.dao;

import daiku.domain.entity.TMakiGoalRelation;
import daiku.domain.model.param.MakiGoalDaoParam;
import daiku.domain.model.res.MakiGoalSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface MakiGoalDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(MakiGoalDaoParam param, SelectOptions options, Collector<MakiGoalSearchModel, ?, R> collector);

    @Select
    Long selectSortNum(Long makiId);

    @Insert
    int insert(TMakiGoalRelation entity);

    @Update(excludeNull = true)
    int update(TMakiGoalRelation entity);
}
