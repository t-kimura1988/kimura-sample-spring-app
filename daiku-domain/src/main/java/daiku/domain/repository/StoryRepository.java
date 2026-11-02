package daiku.domain.repository;

import daiku.domain.dao.StoryDao;
import daiku.domain.entity.TStories;
import daiku.domain.model.param.StoryDaoParam;
import daiku.domain.model.res.StorySearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;

@Repository
public class StoryRepository {
    @Autowired
    StoryDao storyDao;

    public Optional<StorySearchModel> detail(StoryDaoParam param) {
        return storyDao.select(param, SelectOptions.get(), toOptional());
    }

    public Optional<TStories> selectForUpdate(StoryDaoParam param) {
        return storyDao.selectForUpdate(param, SelectOptions.get(), toOptional());
    }

    public void save(TStories stories) {
        Optional.ofNullable(stories.getId())
                .ifPresentOrElse(
                        id -> storyDao.update(stories),
                        () -> storyDao.insert(stories)
                );
    }


}
