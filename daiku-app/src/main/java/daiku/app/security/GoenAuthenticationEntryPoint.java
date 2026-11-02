package daiku.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import daiku.domain.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class GoenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // エラーレスポンスクラスをJSONへ変換するために使う。
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        log.error("user info not found!!!" + authException.toString());
        response.setStatus(HttpStatus.NOT_FOUND.value());

        // Content typeをapplication/jsonとする。
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getOutputStream().println(objectMapper.writeValueAsString(
                ErrorResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(authException.getMessage())
                        .errorCd("E0004").build()));
    }
}
