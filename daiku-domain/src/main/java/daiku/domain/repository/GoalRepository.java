package daiku.domain.repository;

import daiku.domain.dao.GoalDao;
import daiku.domain.entity.TGoals;
import daiku.domain.model.param.AddGoalListParam;
import daiku.domain.model.param.GoalDaoParam;
import daiku.domain.model.res.GoalAddListItemModel;
import daiku.domain.model.res.GoalSearchModel;
import daiku.domain.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class GoalRepository  {
    @Autowired
    GoalDao goalDao;
    public List<GoalSearchModel> search(GoalDaoParam param) {
        return goalDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<GoalSearchModel> detail(GoalDaoParam param) {
        return goalDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public Long selectGoalCount(LocalDate createDate) {
        return goalDao.selectCount(createDate);
    }

    public List<GoalAddListItemModel> addGoalList(AddGoalListParam param) {
        return goalDao.selectAddGoalList(param, SelectOptions.get(), toList());
    }

    public void save(TGoals goals) {
        Optional.ofNullable(goals.getId())
                .ifPresentOrElse(
                        id -> goalDao.update(goals),
                        () -> goalDao.insert(goals)
                );
    }


}
