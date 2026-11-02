package daiku.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoenIntegrityException extends Exception{
    Map detail;
    String errorCd;

    public GoenIntegrityException(String message, Map detail, String errorCd) {
        super(message);
        this.detail = detail;
        this.errorCd = errorCd;
    }

    public GoenIntegrityException(String message, Map detail, Throwable cause) {
        super(message, cause);
        this.detail = detail;
    }


}
