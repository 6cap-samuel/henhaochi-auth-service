package sam.henhaochi.authservice.controllers.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.constants.Role;
import sam.henhaochi.authservice.controllers.responses.TokenResponse;
import sam.henhaochi.authservice.entities.Account;

import static org.junit.jupiter.api.Assertions.*;

class TokenResponseMapperTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    TokenResponseMapper tokenResponseMapper;

    @BeforeEach
    void before() {
        tokenResponseMapper = new TokenResponseMapper();
    }

    @Test
    public void shouldMapAccountToTokenResponse() {
        Role role = Role.ADMIN;

        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .token(TOKEN)
                .role(role)
                .build();

        TokenResponse resposne =
                tokenResponseMapper.map(account);

        assertEquals(
                role,
                resposne.getRole()
        );
    }
}