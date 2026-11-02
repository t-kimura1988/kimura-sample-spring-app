package daiku.domain.repository;

import daiku.domain.dao.MakiDao;
import daiku.domain.entity.TMakis;
import daiku.domain.model.param.MakiDaoParam;
import daiku.domain.model.res.MakiAddGoalListModel;
import daiku.domain.model.res.MakiSearchModel;
import daiku.domain.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class MakiRepository {
    @Autowired
    MakiDao makiDao;

    public List<MakiSearchModel> list(MakiDaoParam param) {
        return  makiDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<MakiSearchModel> detail(MakiDaoParam param) {
        return makiDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public List<MakiAddGoalListModel> makiGoalList(MakiDaoParam param) {
        return makiDao.selectGoalOfMaki(param, SelectOptions.get(), toList());
    }

    public void save(TMakis makis) {
        Optional.ofNullable(makis.getId())
                .ifPresentOrElse(
                        id -> makiDao.update(makis),
                        () -> makiDao.insert(makis)
                );
    }


}
