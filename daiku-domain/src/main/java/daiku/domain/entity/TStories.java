package daiku.domain.entity;

import daiku.domain.listener.TStoriesListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDateTime;

@Data
@Entity(listener = TStoriesListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_stories")
public class TStories {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_stories_id_seq")
    private Long id;
    private Long ideaId;
    private Long accountId;
    private String title;
    private String body;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
