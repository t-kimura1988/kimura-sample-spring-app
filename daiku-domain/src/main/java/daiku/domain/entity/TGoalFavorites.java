package daiku.domain.entity;

import daiku.domain.listener.TGoalFavoritesListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = TGoalFavoritesListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "t_goal_favorites")
public class TGoalFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_goal_favorites_id_seq")
    private Long id;
    private Long goalId;
    private Long accountId;
    private LocalDate favoriteAddDate;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
