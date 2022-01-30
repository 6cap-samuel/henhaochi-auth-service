package sam.henhaochi.authservice.controllers.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.controllers.requests.LoginRequest;
import sam.henhaochi.authservice.controllers.requests.RegisterAccountRequest;
import sam.henhaochi.authservice.entities.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAccountRequestMapperTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    LoginAccountRequestMapper requestMapper;

    @BeforeEach
    void before() {
        requestMapper = new LoginAccountRequestMapper();
    }

    @Test
    public void shouldReturnMappedAccount() {
        Account result = requestMapper.mapToAccountEntity(
                LoginRequest.builder()
                        .username(USERNAME)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build()
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
                EMAIL,
                result.getEmail()
        );
    }
}