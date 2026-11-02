package daiku.domain.repository;

import daiku.domain.dao.IdeaDao;
import daiku.domain.entity.TIdeas;
import daiku.domain.model.param.IdeaDaoParam;
import daiku.domain.model.res.IdeaSearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class IdeaRepository {
    @Autowired
    IdeaDao ideaDao;

    public List<IdeaSearchModel> mySearch(IdeaDaoParam param) {
        return ideaDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<IdeaSearchModel> detail(IdeaDaoParam param) {
        return ideaDao.select(param, SelectOptions.get(), toOptional());
    }

    public Optional<TIdeas> detailForUpdate(IdeaDaoParam param) {
        return ideaDao.selectForUpdate(param, SelectOptions.get(), toOptional());
    }

    public void save(TIdeas ideas) {
        Optional.ofNullable(ideas.getId())
                .ifPresentOrElse(
                        id -> ideaDao.update(ideas),
                        () -> ideaDao.insert(ideas)
                );
    }


}
