package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CheckTokenInputUseCaseTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    CheckTokenInputUseCase checkTokenInputUseCase;
    TokenDataSource tokenDataSource;

    @BeforeEach
    void before() {
        tokenDataSource = mock(TokenDataSource.class);
        checkTokenInputUseCase =
                new CheckTokenInputUseCase(
                        tokenDataSource
                );
    }

    @Test
    public void shouldRetrieveTokenValidity() {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .token(TOKEN)
                .build();

        when(tokenDataSource.isTokenValid(TOKEN))
                .thenReturn(account);
        assertEquals(
                account,
                checkTokenInputUseCase.check(
                        TOKEN
                )
        );
    }
}