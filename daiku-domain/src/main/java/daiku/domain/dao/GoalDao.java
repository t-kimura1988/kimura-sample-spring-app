package daiku.domain.dao;

import daiku.domain.entity.TGoals;
import daiku.domain.model.param.AddGoalListParam;
import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.res.GoalAddListItemModel;
import daiku.domain.model.res.GoalSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.time.LocalDate;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface GoalDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(GoalDaoParam param, SelectOptions options,  Collector<GoalSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectAddGoalList(AddGoalListParam param, SelectOptions options, Collector<GoalAddListItemModel, ?, R> collector);

    @Select
    Long selectCount(LocalDate createDate);

    @Insert
    int insert(TGoals accounts);

    @Update(excludeNull = true)
    int update(TGoals accounts);
}
