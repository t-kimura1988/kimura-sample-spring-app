package daiku.app.service;

import daiku.app.service.input.goalFavorite.FavoriteCreateServiceInput;
import daiku.app.service.input.goalFavorite.GoalFavoriteSearchServiceInput;
import daiku.app.service.output.goalFavorite.GoalFavoriteSearchServiceOutput;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.repository.GoalFavoriteRepository;
import daiku.domain.repository.GoalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoalFavoriteService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    GoalFavoriteRepository goalFavoriteRepository;

    public void create(FavoriteCreateServiceInput input) throws GoenNotFoundException {
        goalRepository.detail(input.toGoalParam()).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Goal.id: ", input.getGoalId().toString());
                    return new GoenNotFoundException("goal detail info no found", param);
                }
        );

        goalFavoriteRepository.selectByAccountIdAndGoalId(input.toGoalFavoriteParam())
                .ifPresentOrElse(
                        goalFavorites -> goalFavoriteRepository.delete(goalFavorites),
                        () -> goalFavoriteRepository.save(input.toEntity())
                );
    }

    public GoalFavoriteSearchServiceOutput list(GoalFavoriteSearchServiceInput input) {
        return GoalFavoriteSearchServiceOutput.builder()
                .goalFavoriteSearchModelList(
                        goalFavoriteRepository.selectList(input.toParam())
                ).build();
    }
}
