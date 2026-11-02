package daiku.app.controller;

import daiku.app.controller.input.processHistory.*;
import daiku.app.service.ProcessHistoryService;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.model.res.ProcessHistoryDuringProcessModel;
import daiku.domain.model.res.ProcessHistorySearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/process-history")
public class ProcessHistoryController {
    @Autowired
    ProcessHistoryService processHistoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProcessHistorySearchModel> list(
            ProcessHistorySearchParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processHistoryService.search(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ProcessHistorySearchModel detail(
            ProcessHistoryDetailParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processHistoryService.detail(param.toService(user.account().getId())).toResponse();
    }

    @PostMapping("/create")
    public ProcessHistorySearchModel create(
            @RequestBody @Validated ProcessHistoryCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return processHistoryService.create(param.toService(user.account().getId()));
    }

    @PostMapping("/update/comment")
    public ProcessHistorySearchModel updateComment(
            @RequestBody @Validated ProcessHistoryUpdateCommentParam param,
            @AuthenticationPrincipal GoenUserDetails user
            ) throws GoenNotFoundException{
        return processHistoryService.updateComment(param.toService(user.account().getId()));
    }

    @PostMapping("/update/status")
    public ProcessHistorySearchModel updateStatus(
            @RequestBody @Validated ProcessHistoryUpdateStatusParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return processHistoryService.updateStatus(param.toService(user.account().getId()));
    }

    @GetMapping("/during-process/list")
    public List<ProcessHistoryDuringProcessModel> scheduleList(
            ProcessHistoryScheduleListParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) {
        return processHistoryService.getScheduleList(param.toService(user.account().getId()));
    }
}
