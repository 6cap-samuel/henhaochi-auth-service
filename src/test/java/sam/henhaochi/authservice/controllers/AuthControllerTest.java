package sam.henhaochi.authservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sam.henhaochi.authservice.controllers.mappers.LoginAccountRequestMapper;
import sam.henhaochi.authservice.controllers.requests.LoginRequest;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.LoginAccountInput;
import sam.henhaochi.authservice.utilities.Json;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoginAccountInput loginAccountInput;

    @MockBean
    LoginAccountRequestMapper loginAccountRequestMapper;

    @Test
    public void shouldReturnProfileWhenAccountIsValid()
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
                .thenReturn(mappedAccount);

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
                .isOk()
        ).andExpect(content()
                .contentType(
                        MediaType.APPLICATION_JSON
                )
        );
    }

    @Test
    public void shouldReturnForbiddeneWhenAccountIsInvalid()
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

}