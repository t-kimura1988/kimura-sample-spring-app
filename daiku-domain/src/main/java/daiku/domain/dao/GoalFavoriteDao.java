package daiku.domain.dao;

import daiku.domain.entity.TGoalFavorites;
import daiku.domain.model.param.GoalFavoriteDaoParam;
import daiku.domain.model.res.GoalFavoriteSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface GoalFavoriteDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(GoalFavoriteDaoParam param, SelectOptions options, Collector<GoalFavoriteSearchModel, ?, R> collector);

    @Insert
    int insert(TGoalFavorites favorites);

    @Update(excludeNull = true)
    int update(TGoalFavorites favorites);

    @Delete(sqlFile = true)
    int delete(TGoalFavorites entity);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectByAccountIdAndGoalId(GoalFavoriteDaoParam param, SelectOptions options, Collector<TGoalFavorites, ?, R> collector);

}
