package daiku.app.service;

import daiku.app.service.input.idea.IdeaCreateServiceInput;
import daiku.app.service.input.idea.IdeaDetailServiceInput;
import daiku.app.service.input.idea.IdeaMySearchServiceInput;
import daiku.app.service.input.idea.IdeaUpdateServiceInput;
import daiku.app.service.output.idea.IdeaCreateServiceOutput;
import daiku.app.service.output.idea.IdeaDetailServiceOutput;
import daiku.app.service.output.idea.IdeaMySearchServiceOutput;
import daiku.domain.entity.TIdeas;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.param.IdeaDaoParam;
import daiku.domain.repository.IdeaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IdeaService {
    @Autowired
    IdeaRepository ideaRepository;

    public IdeaMySearchServiceOutput mySearch(IdeaMySearchServiceInput input) {

        return IdeaMySearchServiceOutput.builder()
                .ideaSearchModelList(ideaRepository.mySearch(input.toDaoParam()))
                .build();
    }

    public IdeaDetailServiceOutput detail(IdeaDetailServiceInput input) throws GoenNotFoundException {
        var detail = ideaRepository.detail(input.toDaoParam()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea detail info no found", param);

        });
        return IdeaDetailServiceOutput.builder()
                .idea(detail)
                .build();
    }

    public IdeaCreateServiceOutput create(IdeaCreateServiceInput input) throws GoenNotFoundException{
        TIdeas entity = input.toEntity();
        ideaRepository.save(entity);
        return IdeaCreateServiceOutput.builder()
                .ideaSearchModel(ideaRepository.detail(IdeaDaoParam.builder().ideaId(entity.getId()).build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Ideas.id: ", entity.getId().toString());
                            return new GoenNotFoundException("idea detail info no found", param);
                        }))
                .build();
    }

    public IdeaCreateServiceOutput update(IdeaUpdateServiceInput input) throws GoenNotFoundException {
        var idea = ideaRepository.detailForUpdate(IdeaDaoParam.builder()
                .ideaId(input.getIdeaId())
                .accountId(input.getAccountId()).build()).orElseThrow(() -> {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("Ideas.id: ", input.getIdeaId().toString());
            return new GoenNotFoundException("idea detail info no found", param);

        });
        idea.setBody(input.getBody());
        ideaRepository.save(idea);
        return IdeaCreateServiceOutput.builder()
                .ideaSearchModel(ideaRepository.detail(IdeaDaoParam.builder().ideaId(idea.getId()).build()).orElseThrow(
                        () -> {
                            Map<String, String> param = new LinkedHashMap<>();
                            param.put("Ideas.id: ", idea.getId().toString());
                            return new GoenNotFoundException("idea detail info no found", param);
                        }))
                .build();
    }
}
