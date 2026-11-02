package daiku.app.controller;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.idea.IdeaDetailParam;
import daiku.app.controller.input.idea.IdeaEditParameter;
import daiku.app.controller.input.idea.IdeaMySearchParam;
import daiku.app.service.IdeaService;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.res.IdeaSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/idea")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping(value = "/my-search")
    public List<IdeaSearchModel> mySearch(
            IdeaMySearchParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) {
        return ideaService.mySearch(param.toService(user.account().getId())).toRes();
    }

    @GetMapping(value = "/detail")
    public IdeaSearchModel detail(IdeaDetailParam param, @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return ideaService.detail(param.toService(user.account().getId())).toRes();
    }

    @PostMapping(value = "/create")
    public IdeaSearchModel create(
            @RequestBody @Validated(CreateGroups.class) IdeaEditParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException{
        return ideaService.create(param.toService(user.account().getId())).toRes();
    }

    @PostMapping(value = "/update")
    public IdeaSearchModel update(
            @RequestBody @Validated(CreateGroups.class) IdeaEditParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException{
        return ideaService.update(param.toUpdateService(user.account().getId())).toRes();
    }

}
