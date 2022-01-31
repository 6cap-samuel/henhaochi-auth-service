package sam.henhaochi.authservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.repositories.entities.AccountEntity;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;
import sam.henhaochi.authservice.repositories.mappers.AccountEntityMapper;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TokenRepositoryAdapterTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    TokenRepositoryAdapter tokenRepositoryAdapter;
    TokenRepository tokenRepository;
    AccountEntityMapper entityMapper;

    @BeforeEach
    void before() {
        tokenRepository = mock(TokenRepository.class);
        entityMapper = mock(AccountEntityMapper.class);
        tokenRepositoryAdapter = new TokenRepositoryAdapter(
                tokenRepository,
                entityMapper
        );
    }

    @Test
    public void shouldSaveNewToken() throws NoSuchAlgorithmException {
        AccountEntity accountEntity = AccountEntity.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        TokenEntity token = TokenEntity.builder()
                .account(accountEntity)
                .tokenString(TOKEN)
                .build();

        when(entityMapper.map(account))
                .thenReturn(accountEntity);

        try(MockedStatic<TokenEntity> tokenEntityMockedStatic =
                    Mockito.mockStatic(TokenEntity.class)) {
            tokenEntityMockedStatic.when(
                    () -> TokenEntity.newInstance(accountEntity)
            ).thenReturn(token);

            assertEquals(
                    TOKEN,
                    tokenRepositoryAdapter.generateToken(account)
            );

            verify(tokenRepository,
                    times(1)
            ).save(token);
        }
    }
}