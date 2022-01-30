package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginAccountInteractorTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    LoginAccountInteractor loginAccountInteractor;
    AccountDataSource accountDataSource;

    @BeforeEach
    void before() {
        accountDataSource = mock(AccountDataSource.class);
        loginAccountInteractor =
                 new LoginAccountInteractor(
                         accountDataSource
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

        assertEquals(
                account,
                loginAccountInteractor.with(
                        account
                )
        );
    }
}