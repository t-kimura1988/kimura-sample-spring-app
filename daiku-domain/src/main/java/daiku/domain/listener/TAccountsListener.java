package daiku.domain.listener;

import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TAccounts;
import lombok.val;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class TAccountsListener<E extends TAccounts> implements EntityListener<E> {

    @Override
    public void preInsert(E entity, PreInsertContext<E> context) {
        val timestamp = LocalDateTime.now();
        entity.setCreatedAt(timestamp);
        entity.setUpdatedAt(timestamp);
    }

    @Override
    public void preUpdate(E entity, PreUpdateContext<E> context) {
        val timestamp = LocalDateTime.now();
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            var account = (GoenUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            entity.setUpdatedBy(account.account().getId());
        } else {
            entity.setUpdatedBy(0L);
        }
        entity.setUpdatedAt(timestamp);
    }


}
