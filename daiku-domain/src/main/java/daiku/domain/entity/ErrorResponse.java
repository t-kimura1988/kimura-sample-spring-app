package daiku.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Value
@Builder
public class ErrorResponse {
    Integer code;
    String message;
    String errorCd;
}
