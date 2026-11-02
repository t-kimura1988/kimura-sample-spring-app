package daiku.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import daiku.app.controller.input.account.AccountCreateParam;
import daiku.app.controller.input.account.AccountUploadParam;
import daiku.domain.enums.AccountImageType;
import daiku.domain.repository.FirebaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class AccountControllerTest extends ControllerBaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    FirebaseRepository firebaseRepository;

    @Test
    void account_show_アカウント存在なし() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/account/show")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_show_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/account/show")
                .with(unAuthorizationAccountType())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_show_アカウント削除済み() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/account/show")
                .with(delAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_show_firebaseアカウント削除済み() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.get("/api/account/show")
                .with(firebaseDelAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_create_アカウント存在なし_正常() throws Exception {
        var request = AccountCreateParam.builder()
                .familyName("test")
                .givenName("test")
                .nickName("test").build();

        String content = objectMapper.writeValueAsString(request);

        doNothing().when(firebaseRepository).accountClaims(any());

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/create")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab,test,test",
            "test,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab,test",
            "test,test,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
    })
    void account_create_authorization_不正(String familyName, String givenName, String nickName) throws Exception {
        var request = AccountCreateParam.builder()
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/create")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_create_authorization_正常() throws Exception {
        var request = AccountCreateParam.builder()
                .familyName("test")
                .givenName("test")
                .nickName("test").build();

        String content = objectMapper.writeValueAsString(request);

        doNothing().when(firebaseRepository).accountClaims(any());

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/create")
                .with(nothingDaikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }

    @Test
    void account_update_アカウント存在なし() throws Exception {
        var request = AccountCreateParam.builder()
                .familyName("test")
                .givenName("test")
                .nickName("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_update_アカウント種別不正() throws Exception {
        var request = AccountCreateParam.builder()
                .familyName("test")
                .givenName("test")
                .nickName("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @CsvSource({
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab,test,test",
            "test,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab,test",
            "test,test,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
    })
    void account_update_authorization_不正(String familyName, String givenName, String nickName) throws Exception {
        var request = AccountCreateParam.builder()
                .familyName(familyName)
                .givenName(givenName)
                .nickName(nickName).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_update_authorization_正常() throws Exception {
        var request = AccountCreateParam.builder()
                .familyName("test")
                .givenName("test")
                .nickName("test").build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/update")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }

    @Test
    void account_delete_アカウント存在なし() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/delete")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_delete_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/delete")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_delete_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/delete")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }

    @Test
    void account_re_update_アカウント存在なし() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/re-update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_re_update_アカウント種別不正() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/re-update")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_re_update_authorization_正常() throws Exception {

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/re-update")
                .with(delAccount())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }

    @Test
    void account_upload_アカウント存在なし() throws Exception {
        var request = AccountUploadParam.builder()
                .imagePath("/account/uid/test.jpeg")
                .imageType(AccountImageType.ACCOUNT_MAIN).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/upload")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }

    @Test
    void account_upload_アカウント種別不正() throws Exception {
        var request = AccountUploadParam.builder()
                .imagePath("/account/uid/test.jpeg")
                .imageType(AccountImageType.ACCOUNT_MAIN).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/upload")
                .with(nonExitAccount())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }


    @ParameterizedTest
    @CsvSource({
            ",0",
            "/account/uid/test.jpeg,a",
    })
    void account_upload_authorization_パラメータ不正(String imagePath, String imageType) throws Exception {
        var request = AccountUploadParam.builder()
                .imagePath(imagePath)
                .imageType(AccountImageType.of(imageType)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/upload")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().is4xxClientError());
    }


    @ParameterizedTest
    @CsvSource({
            "/account/uid/test.jpeg,0",
    })
    void account_upload_authorization_正常(String imagePath, String imageType) throws Exception {
        var request = AccountUploadParam.builder()
                .imagePath(imagePath)
                .imageType(AccountImageType.of(imageType)).build();

        String content = objectMapper.writeValueAsString(request);

        RequestBuilder build = MockMvcRequestBuilders.post("/api/account/upload")
                .with(daikuPrincipal())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(build)
                .andExpect(status().isOk());
    }
}
