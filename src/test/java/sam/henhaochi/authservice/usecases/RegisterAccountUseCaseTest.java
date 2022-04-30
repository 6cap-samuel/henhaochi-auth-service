package sam.henhaochi.authservice.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;
import sam.henhaochi.authservice.usecases.out.UserDetailsDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterAccountUseCaseTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    RegisterAccountUseCase registerAccountUseCase;
    UserDetailsDataSource userDetailsDataSource;
    PasswordEncoder bCryptPasswordEncoder;

//    @BeforeEach
//    void before() {
//        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
//        userDetailsDataSource = mock(UserDetailsDataSource.class);
//        registerAccountUseCase =
//                new RegisterAccountUseCase(
//                        userDetailsDataSource,
//                        bCryptPasswordEncoder
//                );
//    }
//
//    @Test
//    public void shouldReturnAccountCreationStatusWhenCreation() throws NoSuchAlgorithmException {
//        Account account = Account.builder()
//                .email(EMAIL)
//                .username(USERNAME)
//                .password(PASSWORD)
//                .build();
//
//        when(userDetailsDataSource.register(USERNAME, PASSWORD, EMAIL))
//                .thenReturn(AccountCreationStatus.SUCCESS);
//
//        assertEquals(
//                AccountCreationStatus.SUCCESS,
//                registerAccountUseCase.with(
//                        USERNAME,
//                        PASSWORD,
//                        EMAIL
//                )
//        );
//    }
}