package daiku.app.exception;

import lombok.Data;
import lombok.Value;

import java.util.Map;

public class GoenNotFoundException extends Exception{
    Map detail;

    public GoenNotFoundException(String message, Map detail) {
        super(message);
        this.detail = detail;
    }

    public GoenNotFoundException(String message, Map detail, Throwable cause) {
        super(message, cause);
        this.detail = detail;
    }


}
