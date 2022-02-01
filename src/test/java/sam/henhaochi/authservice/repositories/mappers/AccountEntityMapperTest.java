package sam.henhaochi.authservice.repositories.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.repositories.entities.AccountEntity;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountEntityMapperTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    AccountEntityMapper entityMapper;

    @BeforeEach
    void before() {
        entityMapper = new AccountEntityMapper();
    }

    @Test
    public void shouldReturnMappedAccountWithValidAccountEntity() {
        AccountEntity account = AccountEntity.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        Account result = entityMapper.map(
                account
        );

        assertEquals(
                EMAIL,
                result.getEmail()
        );
        assertEquals(
                USERNAME,
                result.getUsername()
        );
        assertEquals(
                PASSWORD,
                result.getPassword()
        );
    }

    @Test
    public void shouldReturnMappedAccountEntityWithValidAccount() {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        AccountEntity result = entityMapper.map(
                account
        );

        assertEquals(
                EMAIL,
                result.getEmail()
        );
        assertEquals(
                USERNAME,
                result.getUsername()
        );
        assertEquals(
                PASSWORD,
                result.getPassword()
        );
    }

    @Test
    public void shouldReturnMappedAccountWithValidTokenEntity()
            throws NoSuchAlgorithmException {
        AccountEntity account = AccountEntity.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        TokenEntity tokenEntity =
                TokenEntity.newInstance(
                        account
                );

        Account result = entityMapper.map(
                tokenEntity
        );

        assertEquals(
                EMAIL,
                result.getEmail()
        );
        assertEquals(
                USERNAME,
                result.getUsername()
        );
        assertEquals(
                PASSWORD,
                result.getPassword()
        );
        assertEquals(
                tokenEntity.getTokenString(),
                result.getToken()
        );
    }
}