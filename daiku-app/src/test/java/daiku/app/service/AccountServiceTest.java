package daiku.app.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuthException;
import daiku.app.DaikuApplication;
import daiku.app.service.input.account.AccountCreateServiceInput;
import daiku.app.service.input.account.AccountReUpdateServiceInput;
import daiku.app.service.input.account.AccountUpdateServiceInput;
import daiku.app.service.input.account.AccountUploadServiceInput;
import daiku.domain.entity.TAccounts;
import daiku.domain.enums.AccountImageType;
import daiku.domain.enums.DelFlg;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.repository.AccountRepository;
import daiku.domain.repository.FirebaseRepository;
import daiku.domain.utils.FirebaseClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest(classes = DaikuApplication.class)
@AutoConfigureMockMvc
@SpringJUnitWebConfig
public class AccountServiceTest {

    @Autowired
    @InjectMocks
    private AccountService accountService;

    @SpyBean
    private AccountRepository accountRepository;

    @SpyBean
    private FirebaseRepository firebaseRepository;

    @SpyBean
    private FirebaseClient firebaseClient;

    @Test
    @DisplayName("selectByUidでデータが取得できなかった場合")
    void test1() {

        var error = assertThrows(GoenNotFoundException.class, () -> {
            accountService.showService("error_uid");
        });
        assertEquals("goal detail info no found", error.getMessage());
    }

    @Test
    @DisplayName("アカウント存在するけど削除されている_d-aicアプリ上")
    void test2() {
        TAccounts accounts = new TAccounts();
        accounts.setId(1L);
        accounts.setUid("11111");
        accounts.setDelFlg(DelFlg.DELETED);
        doReturn(Optional.of(accounts)).when(accountRepository).selectByUid(any());

        var error = assertThrows(GoenIntegrityException.class, () -> {
            accountService.showService("test");
        });

        assertEquals(error.getMessage(), "アカウントが削除済み");
        assertEquals(error.getErrorCd(), "E0001");
    }

    @Test
    @DisplayName("アカウント存在するけど削除されている_firebase連携")
    void test3() {
        TAccounts accounts = new TAccounts();
        accounts.setId(1L);
        accounts.setUid("11111");
        accounts.setDelFlg(DelFlg.FIREBASE_DELETED);
        doReturn(Optional.of(accounts)).when(accountRepository).selectByUid(any());

        var error = assertThrows(GoenIntegrityException.class, () -> {
            accountService.showService("test");
        });

        assertEquals(error.getMessage(), "アカウントが削除済み");
        assertEquals(error.getErrorCd(), "E0005");
    }

    @Test
    @DisplayName("アカウント存在する_正常")
    void test4() throws GoenNotFoundException, GoenIntegrityException {
        TAccounts accounts = new TAccounts();
        accounts.setId(1L);
        accounts.setUid("11111");
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        doReturn(Optional.of(accounts)).when(accountRepository).selectByUid(any());
        var res = accountService.showService("test");

        assertEquals(1L, res.getId());
        assertEquals("11111", res.getUid());
        assertEquals(DelFlg.NOT_DELETED, res.getDelFlg());
    }

    @Test
    @DisplayName("アカウント作成_作成しようとしているUIDが既にアカウント登録されている場合エラーとなることのチェック")
    void test5() {
        var error = assertThrows(GoenNotFoundException.class, () -> {
            accountService.baseCreate(AccountCreateServiceInput.builder()
                    .uid("testuid_111111").build());
        });

        assertEquals(error.getMessage(), "this uid is already existed t_account :");

    }

    @Test
    @DisplayName("アカウント作成_正常")
    void test6() throws GoenNotFoundException, FirebaseAuthException {
        doNothing().when(firebaseRepository).accountClaims(any());
        var res = accountService.baseCreate(AccountCreateServiceInput.builder()
                .uid("new_uid_11111")
                .familyName("test_family")
                .givenName("test_given")
                .nickName("test_nick")
                .email("test@test.test").build());

        assertTrue(res.getId() > 0);
        assertEquals("new_uid_11111", res.getUid());
        assertEquals("test_family", res.getFamilyName());
        assertEquals("test_given", res.getGivenName());
        assertEquals("test_nick", res.getNickName());
    }

    @Test
    @DisplayName("アカウント更新_更新データが存在しない")
    void test7() {
        var error = assertThrows(GoenNotFoundException.class, () -> {
            accountService.update(AccountUpdateServiceInput.builder()
                    .accountId(0L)
                    .uid("testuid_111111")
                    .familyName("test_familyName")
                    .givenName("test_givenName")
                    .nickName("test_nickName")
                    .email("test@test.test")
                    .build());
        });

        assertEquals(error.getMessage(), "account id is not found");

    }

    @Test
    @DisplayName("アカウント更新_更新データが存在しない2_通常発生なし")
    void test8() {
        doReturn(Optional.empty()).when(accountRepository).selectByUid(any());
        var error = assertThrows(GoenNotFoundException.class, () -> {
            accountService.update(AccountUpdateServiceInput.builder()
                    .accountId(1L)
                    .uid("testuid_111111")
                    .familyName("test_familyName")
                    .givenName("test_givenName")
                    .nickName("test_nickName")
                    .email("test@test.test")
                    .build());
        });

        var account = accountRepository.selectById(1L);
        account.ifPresent((account1) -> {
            assertEquals("test", account1.getFamilyName());
            assertEquals("aaaaaa", account1.getGivenName());
        });
        assertEquals("account info no found", error.getMessage());

    }

    @Test
    @DisplayName("アカウント再更新_アカウント情報が削除されていない場合はエラーであることのチェック")
    void test9() {
        var error = assertThrows(GoenNotFoundException.class, () -> {
            TAccounts accounts = new TAccounts();
            accounts.setId(2L);
            accounts.setUid("11111");
            accounts.setDelFlg(DelFlg.NOT_DELETED);
            accountService.reUpdate(AccountReUpdateServiceInput.builder()
                    .account(accounts)
                    .build());
        });
        assertEquals("account is not deleted", error.getMessage());

    }

    @Test
    @DisplayName("アカウント再更新_正常")
    void test10() throws GoenNotFoundException, FirebaseAuthException {
        TAccounts accounts = new TAccounts();
        accounts.setId(2L);
        accounts.setUid("testuid_111112");
        accounts.setDelFlg(DelFlg.DELETED);
        accountService.reUpdate(AccountReUpdateServiceInput.builder()
                .account(accounts)
                .build());
    }

    @Test
    @DisplayName("アカウント画像アップロード項目更新_正常_アカウントメインアイコン")
    void test11() throws GoenNotFoundException, FirebaseAuthException {
        TAccounts accounts = new TAccounts();
        accounts.setId(2L);
        accounts.setUid("testuid_111112");
        accounts.setUserImage("user_image_before");
        accounts.setProfileBackImage("back_image_before");
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        var res = accountService.upload(AccountUploadServiceInput.builder()
                .account(accounts)
                .imageType(AccountImageType.ACCOUNT_MAIN)
                .imagePath("user_image_after")
                .build());

        assertEquals("user_image_after", res.getUserImage());
        assertEquals("back_image_before", res.getProfileBackImage());
    }

    @Test
    @DisplayName("アカウント画像アップロード項目更新_正常_プロフィールバック画像")
    void test12() throws GoenNotFoundException, FirebaseAuthException {
        TAccounts accounts = new TAccounts();
        accounts.setId(2L);
        accounts.setUid("testuid_111112");
        accounts.setUserImage("user_image_before");
        accounts.setProfileBackImage("back_image_before");
        accounts.setDelFlg(DelFlg.NOT_DELETED);
        var res = accountService.upload(AccountUploadServiceInput.builder()
                .account(accounts)
                .imageType(AccountImageType.ACCOUNT_PROFILE_BACK)
                .imagePath("back_image_after")
                .build());

        assertEquals("user_image_before", res.getUserImage());
        assertEquals("back_image_after", res.getProfileBackImage());
    }

    private static FirebaseOptions getMockCredentialOptions() {
        return FirebaseOptions.builder().setCredentials(new MockGoogleCredentials()).build();
    }



    private static class MockGoogleCredentials extends GoogleCredentials {

        @Override
        public AccessToken refreshAccessToken() {
            Date expiry = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
            return new AccessToken(UUID.randomUUID().toString(), expiry);
        }
    }
}
