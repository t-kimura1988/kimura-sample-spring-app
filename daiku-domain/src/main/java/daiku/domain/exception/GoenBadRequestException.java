package daiku.domain.exception;

import lombok.Data;
import lombok.Value;

import java.util.Map;

public class GoenBadRequestException extends Exception {
    private Map detail;
    public GoenBadRequestException(String message, Map detail) {
        super(message);
        this.detail = detail;
    }

    public GoenBadRequestException(String message, Map detail, Throwable cause) {
        super(message, cause);
        this.detail = detail;
    }
}
