package daiku.domain.entity;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Value
@Builder
public class ErrorResponse {
    Integer code;
    String message;
    String errorCd;
}
