package daiku.domain.listener;

import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TIdeas;
import lombok.val;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class TIdeasListener<E extends TIdeas> implements EntityListener<E> {

    @Override
    public void preInsert(E entity, PreInsertContext<E> context) {
        val timestamp = LocalDateTime.now();
        var account = (GoenUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreatedBy(account.account().getId());
        entity.setCreatedAt(timestamp);
        entity.setUpdatedAt(timestamp);
        entity.setUpdatedBy(account.account().getId());
    }

    @Override
    public void preUpdate(E entity, PreUpdateContext<E> context) {
        val timestamp = LocalDateTime.now();
        var account = (GoenUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setUpdatedAt(timestamp);
        entity.setUpdatedBy(account.account().getId());
    }


}
