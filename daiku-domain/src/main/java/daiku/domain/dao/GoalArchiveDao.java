package daiku.domain.dao;

import daiku.domain.entity.TGoalArchive;
import daiku.domain.model.param.GoalArchiveDaoParam;
import daiku.domain.model.res.GoalArchiveSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.time.LocalDate;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface GoalArchiveDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R selectArchive(GoalArchiveDaoParam param, SelectOptions options, Collector<GoalArchiveSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectMyArchive(GoalArchiveDaoParam param, SelectOptions options, Collector<GoalArchiveSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectByGoalId(Long goalId, Collector<TGoalArchive, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectById(GoalArchiveDaoParam param, Collector<TGoalArchive, ?, R> collector);

    @Select
    Long selectCount(LocalDate archiveCreateDate);

    @Insert
    int insert(TGoalArchive archive);

    @Update(excludeNull = true)
    int update(TGoalArchive archive);
}
