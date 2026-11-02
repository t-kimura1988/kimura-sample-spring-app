package daiku.domain.dao;

import daiku.domain.entity.TStoryCharacters;
import daiku.domain.model.param.StoryCharacterDaoParam;
import daiku.domain.model.res.StoryCharacterSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface StoryCharacterDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(StoryCharacterDaoParam param, SelectOptions options, Collector<StoryCharacterSearchModel, ?, R> collector);

    @Insert
    int insert(TStoryCharacters stories);

    @Update(excludeNull = true)
    int update(TStoryCharacters stories);
}
