package daiku.domain.entity;

import daiku.domain.enums.LeaderFlg;
import daiku.domain.listener.TStoryCharactersListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDateTime;

@Data
@Entity(listener = TStoryCharactersListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_story_characters")
public class TStoryCharacters {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_story_characters_id_seq")
    private Long id;
    private Long ideaId;
    private Long storyId;
    private String charaName;
    private String charaDesc;
    private LeaderFlg leaderFlg;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
