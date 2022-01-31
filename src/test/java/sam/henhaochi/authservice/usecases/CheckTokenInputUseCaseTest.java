package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CheckTokenInputUseCaseTest {

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
        when(tokenDataSource.isTokenValid(TOKEN))
                .thenReturn(true);
        assertTrue(
                checkTokenInputUseCase.check(
                        TOKEN
                )
        );
    }
}