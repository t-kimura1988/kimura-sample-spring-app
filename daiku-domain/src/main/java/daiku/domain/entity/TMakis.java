package daiku.domain.entity;

import daiku.domain.listener.TMakisListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDateTime;

@Data
@Entity(listener = TMakisListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_makis")
public class TMakis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_makis_id_seq")
    private Long id;
    private Long accountId;
    private String makiTitle;
    private String makiKey;
    private String makiDesc;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
