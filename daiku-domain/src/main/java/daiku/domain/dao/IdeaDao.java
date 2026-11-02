package daiku.domain.dao;

import daiku.domain.entity.TIdeas;
import daiku.domain.model.param.IdeaDaoParam;
import daiku.domain.model.res.IdeaSearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface IdeaDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(IdeaDaoParam param, SelectOptions options, Collector<IdeaSearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectForUpdate(IdeaDaoParam param, SelectOptions options, Collector<TIdeas, ?, R> collector);

    @Insert
    int insert(TIdeas ideas);

    @Update(excludeNull = true)
    int update(TIdeas ideas);
}
