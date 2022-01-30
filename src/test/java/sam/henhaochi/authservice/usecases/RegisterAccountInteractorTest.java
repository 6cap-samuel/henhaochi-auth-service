package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterAccountInteractorTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    RegisterAccountInteractor registerAccountInteractor;
    AccountDataSource accountDataSource;

    @BeforeEach
    void before() {
        accountDataSource = mock(AccountDataSource.class);
        registerAccountInteractor =
                new RegisterAccountInteractor(
                        accountDataSource
                );
    }

    @Test
    public void shouldReturnAccountCreationStatusWhenCreation() throws NoSuchAlgorithmException {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        when(accountDataSource.registerWith(account))
                .thenReturn(AccountCreationStatus.SUCCESS);

        assertEquals(
                AccountCreationStatus.SUCCESS,
                registerAccountInteractor.with(
                        account
                )
        );
    }

}