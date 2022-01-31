package sam.henhaochi.authservice.controllers.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.controllers.responses.LoginResponse;
import sam.henhaochi.authservice.entities.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAccountResponseMapperTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    LoginAccountResponseMapper responseMapper;

    @BeforeEach
    void before() {
        responseMapper = new LoginAccountResponseMapper();
    }

    @Test
    public void shouldReturnMappedResponseMapper() {
        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .token(TOKEN)
                .build();

        LoginResponse response = responseMapper.map(account);

        assertEquals(
                TOKEN,
                response.getToken()
        );
    }
}