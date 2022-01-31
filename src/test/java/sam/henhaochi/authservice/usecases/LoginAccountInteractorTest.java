package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class LoginAccountInteractorTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    LoginAccountInteractor loginAccountInteractor;
    AccountDataSource accountDataSource;
    TokenDataSource tokenDataSource;

    @BeforeEach
    void before() {
        accountDataSource = mock(AccountDataSource.class);
        tokenDataSource = mock(TokenDataSource.class);
        loginAccountInteractor =
                 new LoginAccountInteractor(
                         accountDataSource,
                         tokenDataSource
                 );
    }

    @Test
    public void shouldReturnAccountWhenLoginIsCalled()
            throws NoSuchAlgorithmException {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        when(accountDataSource.loginWith(account))
                .thenReturn(account);
        when(tokenDataSource.generateToken(account))
                .thenReturn(TOKEN);

        Account result = loginAccountInteractor.with(
                account
        );

        assertEquals(
                account,
                result
        );
        assertEquals(
                TOKEN,
                result.getToken()
        );
    }

    @Test
    public void shouldReturnNullWhenAccountIsInvalid()
            throws NoSuchAlgorithmException {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        when(accountDataSource.loginWith(account))
                .thenReturn(null);

        Account result = loginAccountInteractor.with(
                account
        );

        assertNull(
                result
        );
    }
}