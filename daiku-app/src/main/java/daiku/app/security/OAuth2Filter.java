package daiku.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import daiku.app.service.GoenUserDetailsService;
import daiku.domain.entity.ErrorResponse;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.AccountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class OAuth2Filter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Autowired
    private GoenUserDetailsService userDetailsService;

    @Value("${api.apiKey.spocIos: test}")
    private String iosApiKey;

    public OAuth2Filter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if(Objects.nonNull(context.getAuthentication()) && context.getAuthentication().getPrincipal() instanceof Jwt) {
            UserDetails user = null;
            String uid = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("user_id");
            String email = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("email");
            String apiKey = request.getHeader("X-SPOC-API-KEY");
//            if(apiKey == null || !iosApiKey.equals(apiKey)) {
//                handleApiKeyError(response);
//                return;
//            }
            if(!request.getRequestURI().equals("/api/account/create")) {
                String accountType = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("account_type");
                user = userDetailsService.loadUserByUsername(uid);
                if(accountType == null || Objects.isNull(AccountType.of(accountType))) {
                    handleAccountTypeError(response);
                    return;
                }
            } else {
                var account = new TAccounts();
                account.setUid(uid);
                account.setEmail(email);
                user = new GoenUserDetails(uid, account, AuthorityUtils.createAuthorityList("CREATE_ACCOUNT"));
            }

            var authRequest = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            context.setAuthentication(authRequest);
        }

        filterChain.doFilter(request, response);

    }

    private void handleAccountTypeError(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCd("E0003")
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("アカウント種別が不正です").build();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    }

    private void handleApiKeyError(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCd("E0006")
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Api Keyが不正です").build();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    }
}
