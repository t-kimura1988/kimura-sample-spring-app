package daiku.app.service;

import daiku.app.service.input.story.StoryBodyUpdateServiceInput;
import daiku.app.service.input.story.StoryCreateServiceInput;
import daiku.app.service.input.story.StoryDetailServiceInput;
import daiku.app.service.output.story.StoryBodyUpdateServiceOutput;
import daiku.app.service.output.story.StoryCreateServiceOutput;
import daiku.app.service.output.story.StoryDetailServiceOutput;
import daiku.domain.entity.TStories;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.param.IdeaDaoParam;
import daiku.domain.model.param.StoryDaoParam;
import daiku.domain.repository.IdeaRepository;
import daiku.domain.repository.StoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoryService {
    @Autowired
    StoryRepository storyRepository;

    @Autowired
    IdeaRepository ideaRepository;

    public StoryDetailServiceOutput detail(StoryDetailServiceInput input) throws GoenNotFoundException {

        ideaRepository.detail(IdeaDaoParam.builder().ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea is not found", param);
        });

        return StoryDetailServiceOutput.builder()
                .storySearchModel(storyRepository.detail(input.toDaoParam()).orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Story.id: ", input.getStoryId().toString());
                    return new GoenNotFoundException("story is not found", param);
                }))
                .build();
    }

    public StoryCreateServiceOutput create(StoryCreateServiceInput input) throws GoenNotFoundException{
        ideaRepository.detail(IdeaDaoParam.builder().ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea is not found", param);
        });
        var story = storyRepository.detail(StoryDaoParam.builder().ideaId(input.getIdeaId()).build());
        if(story.isPresent()) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            throw  new GoenNotFoundException("idea of story already existed", param);
        }
        TStories entity = input.toEntity();
        storyRepository.save(entity);
        return StoryCreateServiceOutput.builder()
                .storySearchModel(storyRepository.detail(StoryDaoParam.builder()
                        .storyId(entity.getId()).build())
                        .orElseThrow(() -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Ideas.id: ", entity.getId().toString());
                            return new GoenNotFoundException("idea detail info no found", param);
                        })).build();
    }

    public StoryBodyUpdateServiceOutput updateBody(StoryBodyUpdateServiceInput input) throws GoenNotFoundException {
        ideaRepository.detail(IdeaDaoParam.builder().ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea is not found", param);
        });

        var story = storyRepository.selectForUpdate(StoryDaoParam.builder()
                .storyId(input.getStoryId()).ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Story.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("story is not found", param);
        });

        story.setBody(input.getBody());

        storyRepository.save(story);

        return StoryBodyUpdateServiceOutput.builder()
                .ideaSearchModel(ideaRepository.detail(IdeaDaoParam.builder()
                        .ideaId(story.getIdeaId()).build()).orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("Idea.id: ", input.getIdeaId().toString());
                    return new GoenNotFoundException("idea is not found", param);
                }))
                .build();
    }
}
