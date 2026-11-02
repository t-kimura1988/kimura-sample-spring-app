package daiku.app.controller;

import daiku.app.controller.input.goalFavorite.GoalFavoriteCreateParam;
import daiku.app.controller.input.goalFavorite.GoalFavoriteSearchParameter;
import daiku.app.service.GoalFavoriteService;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.model.res.GoalFavoriteSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/goal-favorite")
public class GoalFavoriteController {

    @Autowired
    GoalFavoriteService goalFavoriteService;

    @PostMapping("/change")
    public void favoriteCreate(
            @RequestBody @Validated GoalFavoriteCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user
            ) throws GoenNotFoundException {
        goalFavoriteService.create(param.toService(user.account().getId()));

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<GoalFavoriteSearchModel> search(GoalFavoriteSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        log.info("goal controller start");
        return goalFavoriteService.list(param.toService(user.account().getId())).toResponse();
    }
}
