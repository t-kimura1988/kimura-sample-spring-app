package daiku.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoenNotFoundException extends Exception{
    Map detail;
    String errorCd;

    public GoenNotFoundException(String message, Map detail) {
        super(message);
        this.detail = detail;
    }

    public GoenNotFoundException(String message, Map detail, String errorCd) {
        super(message);
        this.detail = detail;
        this.errorCd = errorCd;
    }

    public GoenNotFoundException(String message, Map detail, Throwable cause) {
        super(message, cause);
        this.detail = detail;
    }


}
