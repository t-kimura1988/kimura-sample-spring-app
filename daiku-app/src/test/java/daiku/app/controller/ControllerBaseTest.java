package daiku.app.controller;

import daiku.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerBaseTest {


    @Autowired
    private AccountRepository accountRepository;

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor daikuPrincipal() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_111111")
                .claim("email", "aaaaa@aaa.aaa")
                .claim("account_type", "0")
                .build();
        return jwt().jwt(jwt);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor delAccount() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_111112")
                .claim("email", "aaaaa@aaa.aaa")
                .claim("account_type", "0")
                .build();
        return jwt().jwt(jwt);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor firebaseDelAccount() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_111113")
                .claim("email", "aaaaa@aaa.aaa")
                .claim("account_type", "0")
                .build();
        return jwt().jwt(jwt);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor nonExitAccount() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_nonaccount")
                .claim("email", "aaaaa@aaa.aaa")
                .claim("account_type", "0")
                .build();
        return jwt().jwt(jwt);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor unAuthorizationAccountType() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_111111")
                .claim("email", "aaaaa@aaa.aaa")
                .claim("account_type", "999999")
                .build();
        return jwt().jwt(jwt);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor nothingDaikuPrincipal() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("scope", "message:read")
                .claim("user_id", "testuid_911111")
                .claim("email", "aaaaa9@aaa.aaa")
                .build();
        return jwt().jwt(jwt);
    }

}
