package sam.henhaochi.authservice.repositories.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class TokenEntityTest {

    TokenEntity tokenEntity;

    @BeforeEach
    void before() {
        tokenEntity = new TokenEntity();
    }

    @Test
    public void shouldReturnNewInstance()
            throws NoSuchAlgorithmException {
        AccountEntity accountEntity = AccountEntity.builder()
                .build();

        TokenEntity tokenEntity = TokenEntity.newInstance(
                accountEntity
        );

        assertEquals(
                accountEntity,
                tokenEntity.getAccount()
        );

        assertNotNull(
                tokenEntity.getTokenString()
        );

        assertNotNull(
                tokenEntity.getExpirationDate()
        );
    }
}