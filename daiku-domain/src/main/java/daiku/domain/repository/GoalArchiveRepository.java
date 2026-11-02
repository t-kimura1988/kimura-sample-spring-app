package daiku.domain.repository;

import daiku.domain.dao.GoalArchiveDao;
import daiku.domain.dao.GoalDao;
import daiku.domain.entity.TGoalArchive;
import daiku.domain.model.param.GoalArchiveDaoParam;
import daiku.domain.model.res.GoalArchiveSearchModel;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class GoalArchiveRepository {
    @Autowired
    GoalDao goalDao;
    @Autowired
    GoalArchiveDao goalArchiveDao;

    public List<GoalArchiveSearchModel> archiveSearch(GoalArchiveDaoParam param) {
        return goalArchiveDao.selectArchive(param, SelectOptions.get(), toList());
    }

    public List<GoalArchiveSearchModel> myArchiveSearch(GoalArchiveDaoParam param) {
        return goalArchiveDao.selectMyArchive(param, SelectOptions.get(), toList());
    }

    public Optional<GoalArchiveSearchModel> archiveOptional(GoalArchiveDaoParam param) {
        return goalArchiveDao.selectArchive(param, SelectOptions.get(), toOptional());
    }

    public Optional<GoalArchiveSearchModel> myArchiveOptional(GoalArchiveDaoParam param) {
        return goalArchiveDao.selectMyArchive(param, SelectOptions.get(), toOptional());
    }

    public Optional<TGoalArchive> selectByGoalId(Long goalId) {
        return goalArchiveDao.selectByGoalId(goalId, toOptional());
    }

    public Optional<TGoalArchive> selectById(GoalArchiveDaoParam param) {
        return goalArchiveDao.selectById(param, toOptional());
    }

    public Long selectGoalArchiveCount(LocalDate archiveCreateDate) {
        return goalDao.selectCount(archiveCreateDate);
    }

    public void save(TGoalArchive archive) {
        Optional.ofNullable(archive.getId())
                .ifPresentOrElse(
                        id -> goalArchiveDao.update(archive),
                        () -> goalArchiveDao.insert(archive)
                );
    }


}
