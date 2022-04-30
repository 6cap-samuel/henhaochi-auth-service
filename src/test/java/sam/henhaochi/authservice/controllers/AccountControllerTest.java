package sam.henhaochi.authservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.controllers.mappers.RegisterAccountRequestMapper;
import sam.henhaochi.authservice.controllers.requests.RegisterAccountRequest;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.RegisterAccountInput;
import sam.henhaochi.authservice.utilities.Json;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterAccountInput registerAccountInput;

    @MockBean
    private RegisterAccountRequestMapper requestMapper;

//    @Test
//    public void shouldReturnCreatedStatusWithValidAccountInformation()
//            throws Exception {
//        Account mappedAccount = Account.builder()
//                .username(USERNAME)
//                .password(PASSWORD)
//                .email(EMAIL)
//                .build();
//
//        when(requestMapper.mapToAccountEntity(any())).thenReturn(
//                mappedAccount
//        );
//        when(registerAccountInput.with(USERNAME, PASSWORD, EMAIL)).thenReturn(
//                AccountCreationStatus.SUCCESS
//        );
//
//        RegisterAccountRequest request =
//                RegisterAccountRequest.builder()
//                        .username(USERNAME)
//                        .email(EMAIL)
//                        .password(PASSWORD)
//                        .build();
//
//        mockMvc.perform(post("/accounts/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Json.toJson(request))
//        ).andExpect(status()
//                .isCreated()
//        );
//    }
//
//    @Test
//    public void shouldReturnConflictStatusWithInvalidAccountInformation()
//            throws Exception {
//        Account mappedAccount = Account.builder()
//                .username(USERNAME)
//                .password(PASSWORD)
//                .email(EMAIL)
//                .build();
//
//        when(requestMapper.mapToAccountEntity(any())).thenReturn(
//                mappedAccount
//        );
//        when(registerAccountInput.with(USERNAME, PASSWORD, EMAIL)).thenReturn(
//                AccountCreationStatus.FAIL
//        );
//
//        RegisterAccountRequest request =
//                RegisterAccountRequest.builder()
//                        .username(USERNAME)
//                        .email(EMAIL)
//                        .password(PASSWORD)
//                        .build();
//
//        mockMvc.perform(post("/accounts/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Json.toJson(request))
//        ).andExpect(status()
//                .isConflict()
//        );
//    }
}