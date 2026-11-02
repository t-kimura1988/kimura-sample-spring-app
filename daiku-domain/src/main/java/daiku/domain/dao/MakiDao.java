package daiku.domain.dao;

import daiku.domain.entity.TMakis;
import daiku.domain.model.param.MakiDaoParam;
import daiku.domain.model.res.MakiAddGoalListModel;
import daiku.domain.model.res.MakiSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface MakiDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R selectById(MakiDaoParam param, SelectOptions options, Collector<MakiSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R select(MakiDaoParam param, SelectOptions options, Collector<MakiSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectGoalOfMaki(MakiDaoParam param, SelectOptions options, Collector<MakiAddGoalListModel, ?, R> collector);

    @Insert
    int insert(TMakis makis);

    @Update(excludeNull = true)
    int update(TMakis makis);
}
