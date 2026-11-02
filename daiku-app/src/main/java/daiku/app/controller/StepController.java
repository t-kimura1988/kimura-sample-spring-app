package daiku.app.controller;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.groups.UpdateGroups;
import daiku.app.controller.input.step.StepDateDetailParameter;
import daiku.app.controller.input.step.StepEditParameter;
import daiku.app.service.StepService;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.res.StepSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/step")
public class StepController {

    @Autowired
    private StepService stepService;

    @PostMapping(value = "/create")
    public StepSearchModel create(
            @RequestBody @Validated(CreateGroups.class) StepEditParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return stepService.create(param.toService(user.account().getId())).toRes();
    }

    @PostMapping(value = "/update")
    public StepSearchModel update(
            @RequestBody @Validated(UpdateGroups.class) StepEditParameter param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return stepService.update(param.toUpdateService(user.account().getId())).toRes();
    }

    @GetMapping(value = "/date-detail")
    public StepSearchModel dateDetail(
            @Validated StepDateDetailParameter params,
            @AuthenticationPrincipal GoenUserDetails user
            ) throws GoenNotFoundException {
        return stepService.detail(params.toService(user.account().getId())).toRes();
    }
}
