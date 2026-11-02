package daiku.domain.dao;

import daiku.domain.entity.TProcessesHistory;
import daiku.domain.model.param.ProcessHistoryDaoParam;
import daiku.domain.model.param.ProcessHistoryDuringProcessParam;
import daiku.domain.model.res.ProcessHistoryDuringProcessModel;
import daiku.domain.model.res.ProcessHistorySearchModel;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface ProcessHistoryDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(ProcessHistoryDaoParam param, SelectOptions options, Collector<ProcessHistorySearchModel, ?, R> collector);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectDuringProcess(ProcessHistoryDuringProcessParam param, SelectOptions options, Collector<ProcessHistoryDuringProcessModel, ?, R> collector);

    @Insert
    int insert(TProcessesHistory process);

    @Update(excludeNull = true)
    int update(TProcessesHistory process);
}
