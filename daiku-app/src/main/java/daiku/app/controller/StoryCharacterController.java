package daiku.app.controller;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.storyCharacters.StoryCharacterEditParameter;
import daiku.app.controller.input.storyCharacters.StoryCharacterListParameter;
import daiku.app.service.StoryCharacterService;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.res.StoryCharacterSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/story-character")
public class StoryCharacterController {

    @Autowired
    private StoryCharacterService storyCharacterService;

    @PostMapping(value = "/create")
    public StoryCharacterSearchModel create(
            @RequestBody @Validated(CreateGroups.class) StoryCharacterEditParameter param) throws GoenNotFoundException{
        return storyCharacterService.create(param.toService()).toRes();
    }

    @GetMapping(value = "/list")
    public List<StoryCharacterSearchModel> list(StoryCharacterListParameter param) throws GoenNotFoundException {
        return storyCharacterService.list(param.toService()).toRes();

    }
}
