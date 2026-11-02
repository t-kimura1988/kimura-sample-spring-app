package daiku.domain.repository;

import daiku.domain.dao.StoryCharacterDao;
import daiku.domain.entity.TStoryCharacters;
import daiku.domain.model.param.StoryCharacterDaoParam;
import daiku.domain.model.res.StoryCharacterSearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class StoryCharacterRepository {
    @Autowired
    StoryCharacterDao storyCharacterDao;

    public List<StoryCharacterSearchModel> getStoryCharacters(StoryCharacterDaoParam param) {
        return storyCharacterDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<StoryCharacterSearchModel> detail(StoryCharacterDaoParam param) {
        return storyCharacterDao.select(param, SelectOptions.get(), toOptional());
    }

    public List<StoryCharacterSearchModel> getStoryLeader(StoryCharacterDaoParam param) {
        return storyCharacterDao.select(param, SelectOptions.get(), toList());
    }

    public void save(TStoryCharacters entity) {
        Optional.ofNullable(entity.getId())
                .ifPresentOrElse(
                        id -> storyCharacterDao.update(entity),
                        () -> storyCharacterDao.insert(entity)
                );
    }


}
