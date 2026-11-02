package daiku.app.controller;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.maki.*;
import daiku.app.service.MakiService;
import daiku.app.service.input.maki.MakiAddGoalServiceInput;
import daiku.domain.exception.GoenBadRequestException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TMakiGoalRelation;
import daiku.domain.model.res.GoalAddListItemModel;
import daiku.domain.model.res.MakiAddGoalListModel;
import daiku.domain.model.res.MakiSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/maki")
public class MakiController {

    @Autowired
    private MakiService makiService;

    @PostMapping(value = "/create")
    public MakiSearchModel create(
            @RequestBody @Validated(CreateGroups.class) MakiCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException{
        return makiService.create(param.toCreateService(user.account().getId()));
    }

    @GetMapping(value =  "/search")
    public List<MakiSearchModel> search(@Validated MakiSearchParam param,
                                        @AuthenticationPrincipal GoenUserDetails user) {
        return makiService.search(param.toService(user.account().getId())).toResponse();

    }

    @GetMapping(value = "/detail")
    public MakiSearchModel detail(@Validated MakiDetailParam param,
                                  @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenBadRequestException {
        return makiService.detail(param.toService(user.account().getId())).toResponse();

    }

    @GetMapping(value =  "/goal-list")
    public List<MakiAddGoalListModel> goalList(@Validated MakiGoalListParam param,
                                               @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return makiService.goalList(param.toService(user.account().getId())).toResponse();

    }

    @GetMapping(value =  "/add/goal-list")
    public List<GoalAddListItemModel> addGoalList(@Validated MakiAddGoalListParam param,
                                                  @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return makiService.addGoalList(param.toService(user.account().getId())).toResponse();
    }

    @PostMapping(value = "/add/goal")
    public List<TMakiGoalRelation> addGoal(@Validated @RequestBody List<MakiAddGoalParam> param,
                                           @AuthenticationPrincipal GoenUserDetails user) {
        return makiService.makiAddGoal(MakiAddGoalServiceInput.builder()
                .body(param)
                .accountId(user.account().getId()).build()).toResponse();
    }
}
