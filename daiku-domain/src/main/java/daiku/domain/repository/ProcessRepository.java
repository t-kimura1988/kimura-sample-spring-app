package daiku.domain.repository;

import daiku.domain.dao.ProcessDao;
import daiku.domain.entity.TProcesses;
import daiku.domain.model.param.ProcessDaoParam;
import daiku.domain.model.res.ProcessSearchModel;
import daiku.domain.utils.CollectorUtil;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class ProcessRepository {
    @Autowired
    ProcessDao processDao;

    public List<ProcessSearchModel> search(ProcessDaoParam param) {
        return processDao.select(param, SelectOptions.get(), toList());
    }

    public Optional<ProcessSearchModel> detail(ProcessDaoParam param) {
        return processDao.select(param, SelectOptions.get(), CollectorUtil.toOptional());
    }

    public void save(TProcesses process) {
        Optional.ofNullable(process.getId())
                .ifPresentOrElse(
                        id -> processDao.update(process),
                        () -> processDao.insert(process)
                );
    }


}
