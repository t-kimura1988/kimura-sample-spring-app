package daiku.app.service;

import daiku.app.service.input.storyCharacter.StoryCharacterCreateServiceInput;
import daiku.app.service.input.storyCharacter.StoryCharacterListServiceInput;
import daiku.app.service.output.storyCharacter.StoryCharacterCreateServiceOutput;
import daiku.app.service.output.storyCharacter.StoryCharacterListServiceOutput;
import daiku.domain.entity.TStoryCharacters;
import daiku.domain.enums.LeaderFlg;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.param.IdeaDaoParam;
import daiku.domain.model.param.StoryCharacterDaoParam;
import daiku.domain.model.param.StoryDaoParam;
import daiku.domain.repository.IdeaRepository;
import daiku.domain.repository.StoryCharacterRepository;
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
public class StoryCharacterService {
    @Autowired
    StoryRepository storyRepository;

    @Autowired
    IdeaRepository ideaRepository;

    @Autowired
    StoryCharacterRepository storyCharacterRepository;

    public StoryCharacterCreateServiceOutput create(StoryCharacterCreateServiceInput input) throws GoenNotFoundException{
        ideaRepository.detail(IdeaDaoParam.builder().ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea is not found", param);
        });
        storyRepository.detail(StoryDaoParam.builder().storyId(input.getStoryId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Story.id: ", input.getStoryId().toString());
            return new GoenNotFoundException("story is not found", param);
        });
        if(input.getLeaderFlg().equals(LeaderFlg.LEADER))  {
            var charas = storyCharacterRepository.getStoryLeader(StoryCharacterDaoParam.builder()
                    .storyId(input.getStoryId())
                    .ideaId(input.getIdeaId())
                    .leaderFlg(LeaderFlg.LEADER).build());
            if(!charas.isEmpty()) {
                Map<String, String> param = new LinkedHashMap<>();
                param.put("Ideas.id: ", input.getIdeaId().toString());
                param.put("Story.id: ", input.getStoryId().toString());
                throw  new GoenNotFoundException("story_character's leader is already existed", param);
            }
        }
        TStoryCharacters entity = input.toEntity();
        storyCharacterRepository.save(entity);
        return StoryCharacterCreateServiceOutput.builder()
                .storyCharacterSearchModel(
                        storyCharacterRepository.detail(StoryCharacterDaoParam.builder()
                        .storyCharacterId(entity.getId()).build()).orElseThrow(() -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("StoryCharacter.id: ", entity.getId().toString());
                            return new GoenNotFoundException("story character is not found", param);
                        })).build();
    }

    public StoryCharacterListServiceOutput list(StoryCharacterListServiceInput input) throws GoenNotFoundException {

        ideaRepository.detail(IdeaDaoParam.builder().ideaId(input.getIdeaId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea is not found", param);
        });
        storyRepository.detail(StoryDaoParam.builder().storyId(input.getStoryId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Story.id: ", input.getStoryId().toString());
            return new GoenNotFoundException("story is not found", param);
        });

        return StoryCharacterListServiceOutput.builder()
                .storyCharacterSearchModel(storyCharacterRepository.getStoryCharacters(StoryCharacterDaoParam.builder()
                        .ideaId(input.getIdeaId())
                        .storyId(input.getStoryId()).build())).build();
    }
}
