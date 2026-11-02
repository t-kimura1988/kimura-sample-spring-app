package daiku.domain.repository;

import daiku.domain.dao.AccountDao;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.DelFlg;
import daiku.domain.model.param.AccountDaoParam;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static daiku.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class AccountRepository {

    @Autowired
    private AccountDao accountDao;

    public Optional<TAccounts> selectByUid(String uid) {
        return accountDao.selectByUid(uid);
    }

    public Optional<TAccounts> selectById(Long accountId){
        return accountDao.select(AccountDaoParam.builder().id(accountId).build(), SelectOptions.get(), toOptional());
    }

    public List<TAccounts> selectOfDel() {
        return accountDao.select(AccountDaoParam.builder()
                .delFlg(DelFlg.DELETED)
                .updatedAt(LocalDateTime.now().minusMonths(1)).build(), SelectOptions.get(), toList());
    }

    public Long accountCount() {
        return accountDao.selectCount();
    }
    public int save(TAccounts accounts) {
        if(accounts.getId() == null) {
            return accountDao.insert(accounts);
        }else {
            return accountDao.update(accounts);
        }
    }
}
