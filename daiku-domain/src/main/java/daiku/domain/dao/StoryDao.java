package daiku.domain.dao;

import daiku.domain.entity.TStories;
import daiku.domain.model.param.StoryDaoParam;
import daiku.domain.model.res.StorySearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface StoryDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(StoryDaoParam param, SelectOptions options, Collector<StorySearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R  selectForUpdate(StoryDaoParam param, SelectOptions options, Collector<TStories, ?, R> collector);

    @Insert
    int insert(TStories stories);

    @Update(excludeNull = true)
    int update(TStories stories);
}
