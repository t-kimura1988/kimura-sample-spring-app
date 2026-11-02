package daiku.app.service.input.idea;

import daiku.domain.entity.TIdeas;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdeaCreateServiceInput {
    Long accountId;
    String body;

    public TIdeas toEntity() {
        TIdeas ideas = new TIdeas();
        ideas.setAccountId(accountId);
        ideas.setBody(body);
        return ideas;
    }
}
