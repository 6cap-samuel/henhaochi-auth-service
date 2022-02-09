package sam.henhaochi.authservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sam.henhaochi.authservice.constants.Role;
import sam.henhaochi.authservice.controllers.mappers.LoginAccountRequestMapper;
import sam.henhaochi.authservice.controllers.mappers.LoginAccountResponseMapper;
import sam.henhaochi.authservice.controllers.mappers.TokenResponseMapper;
import sam.henhaochi.authservice.controllers.requests.LoginRequest;
import sam.henhaochi.authservice.controllers.responses.LoginResponse;
import sam.henhaochi.authservice.controllers.responses.TokenResponse;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.CheckTokenInput;
import sam.henhaochi.authservice.usecases.in.LoginAccountInput;
import sam.henhaochi.authservice.utilities.Json;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";
    private static String TOKEN = "mocktoken";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoginAccountInput loginAccountInput;

    @MockBean
    LoginAccountRequestMapper loginAccountRequestMapper;

    @MockBean
    LoginAccountResponseMapper loginAccountResponseMapper;

    @MockBean
    TokenResponseMapper tokenResponseMapper;

    @MockBean
    CheckTokenInput checkTokenInput;

    @Test
    public void shouldReturnProfileWhenAccountIsValid()
            throws Exception {

        Role role = Role.ADMIN;

        Account mappedAccount = Account.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .role(role)
                .build();

        when(loginAccountRequestMapper.mapToAccountEntity(
                any()
        )).thenReturn(
                mappedAccount
        );

        when(loginAccountInput.with(mappedAccount))
                .thenReturn(mappedAccount);

        LoginRequest request = LoginRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        LoginResponse response = LoginResponse
                .builder()
                .token(TOKEN)
                .role(role)
                .build();

        when(loginAccountResponseMapper.map(
                mappedAccount)
        ).thenReturn(response);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.toJson(request)
                )
        ).andExpect(status()
                .isOk()
        ).andExpect(content()
                .contentType(
                        MediaType.APPLICATION_JSON
                )
        ).andExpect(jsonPath("$.token")
                .exists()
        ).andExpect(jsonPath("$.token")
                .value(TOKEN)
        ).andExpect(jsonPath("$.role")
                .exists()
        );
    }

    @Test
    public void shouldReturnForbiddenWhenAccountIsInvalid()
            throws Exception {
        Account mappedAccount = Account.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        when(loginAccountRequestMapper.mapToAccountEntity(
                any()
        )).thenReturn(
                mappedAccount
        );

        when(loginAccountInput.with(mappedAccount))
                .thenReturn(null);

        LoginRequest request = LoginRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.toJson(request)
                )
        ).andExpect(status()
                .isForbidden()
        ).andExpect(content()
                .contentType(
                        MediaType.APPLICATION_JSON
                )
        );
    }

    @Test
    public void shouldReturnNoContentWhenTokenIsValid() throws Exception {

        Role expectedRole = Role.ADMIN;

        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .token(TOKEN)
                .role(expectedRole)
                .build();

        when(checkTokenInput.check(TOKEN)).thenReturn(account);

        TokenResponse response = TokenResponse.builder()
                .role(expectedRole)
                .build();

        when(tokenResponseMapper.map(account)).thenReturn(response);

        mockMvc.perform(get("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", TOKEN)
        ).andExpect(status()
                .isOk()
        ).andExpect(jsonPath(
                "$.role"
                ).exists()
        );
    }

    @Test
    public void shouldReturnForbiddenWhenTokenIsInvalid() throws Exception {
        when(checkTokenInput.check(TOKEN)).thenReturn(null);

        mockMvc.perform(get("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", TOKEN)
        ).andExpect(status()
                .isForbidden()
        );
    }
}