package daiku.domain.entity;

import daiku.domain.listener.TIdeasListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDateTime;

@Data
@Entity(listener = TIdeasListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_ideas")
public class TIdeas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_ideas_id_seq")
    private Long id;
    private Long accountId;
    private String body;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
