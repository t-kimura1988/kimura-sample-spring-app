package daiku.app.controller;

import daiku.app.controller.input.goal.*;
import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.controller.output.GoalArchiveDetailResponse;
import daiku.app.service.GoalService;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TGoalArchive;
import daiku.domain.model.res.GoalArchiveSearchModel;
import daiku.domain.model.res.GoalSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping(value = "/search")
    public List<GoalSearchModel> search(GoalSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        return goalService.search(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public GoalSearchModel detail(@Validated GoalDetailParameter param,
                                  @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.detail(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public GoalSearchModel create(
            @RequestBody @Validated(CreateGroups.class) GoalCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException{
        return goalService.create(param.toCreateService(user.account().getId()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public GoalSearchModel update(
            @RequestBody @Validated(UpdateGroups.class) GoalCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.update(param.toUpdateService(user.account().getId()));
    }

    @RequestMapping(value = "/create/archive", method = RequestMethod.POST)
    public TGoalArchive archive(
            @RequestBody @Validated(CreateGroups.class) GoalArchiveCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archive(param.toArchiveService(user.account().getId()));
    }

    @RequestMapping(value = "/update/archive/display", method = RequestMethod.GET)
    public TGoalArchive archiveUpdate(
            GoalArchiveDetailParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archiveUpdateDisp(param.toArchiveUpdateDipsService()).toResponse();
    }

    @RequestMapping(value = "/update/archive", method = RequestMethod.POST)
    public TGoalArchive archiveUpdateDisp(
            @RequestBody @Validated(UpdateGroups.class) GoalArchiveCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archiveUpdate(param.toArchiveUpdateService());
    }

    @RequestMapping(value = "/archive/search", method = RequestMethod.GET)
    public List<GoalArchiveSearchModel> archiveSearch(GoalArchiveSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        return goalService.archiveSearch(param.toService(user.account().getId())).toResponse();
    }

    @GetMapping("/my-archive/list")
    public List<GoalArchiveSearchModel> myArchiveList(MyGoalArchiveSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        return goalService.myArchiveSearch(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/archive/detail", method = RequestMethod.GET)
    public GoalArchiveDetailResponse archiveDetail(GoalArchiveDetailParameter param, @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.archiveDetail(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/my-archive/detail", method = RequestMethod.GET)
    public GoalArchiveDetailResponse myArchiveDetail(GoalArchiveDetailParameter param, @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.myArchiveDetail(param.toMyArchiveService(user.account().getId())).toResponse();
    }

    @GetMapping("/archive/edit-disp")
    public GoalArchiveSearchModel archiveEditDisplay(GoalArchiveEditDispParameter param, @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.getArchiveEdit(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/update/archive/updating-flg", method = RequestMethod.POST)
    public GoalSearchModel archiveUpdatingFlg(
            @RequestBody @Validated(UpdateGroups.class) GoalArchiveUpdatingFlgParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archiveUpdatingFlg(param.toService());
    }
}
