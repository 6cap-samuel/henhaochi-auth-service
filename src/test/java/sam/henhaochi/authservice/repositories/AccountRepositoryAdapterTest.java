package sam.henhaochi.authservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.repositories.entities.AccountEntity;
import sam.henhaochi.authservice.repositories.mappers.AccountEntityMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountRepositoryAdapterTest {

    private static String USERNAME = "johndoe";
    private static String EMAIL = "johndoe@gmail.com";
    private static String PASSWORD = "johndoeisnice";

    AccountRepositoryAdapter accountRepositoryAdapter;
    AccountRepository accountRepository;
    AccountEntityMapper entityMapper;

    @BeforeEach
    void before() {
        accountRepository = mock(AccountRepository.class);
        entityMapper = mock(AccountEntityMapper.class);
        accountRepositoryAdapter = new AccountRepositoryAdapter(
                accountRepository,
                entityMapper
        );
    }

    @Test
    public void shouldReturnSuccessUponRegisterAccountAndEmailDoesNotExists() {
        when(accountRepository.existsByEmailEqualsIgnoreCase(EMAIL))
                .thenReturn(false);

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

        when(entityMapper.map(
                account
        )).thenReturn(accountEntity);

        assertEquals(
                AccountCreationStatus.SUCCESS,
                accountRepositoryAdapter.registerWith(
                        account
                )
        );

        verify(accountRepository,
                times(1)
        ).save(accountEntity);
    }

    @Test
    public void shouldReturnFailUponRegisterAccountAsEmailExists() {
        when(accountRepository.existsByEmailEqualsIgnoreCase(EMAIL))
                .thenReturn(true);

        Account account = Account.builder()
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        assertEquals(
                AccountCreationStatus.FAIL,
                accountRepositoryAdapter.registerWith(
                        account
                )
        );

        verify(accountRepository,
                times(0)
        ).save(any());
    }


    @Test
    public void shouldReturnValidAccountUponLoginCredentialsAreCorrect() {
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

        Optional<AccountEntity> optionalAccountEntity =
                Optional.of(
                        accountEntity
                );

        when(accountRepository.login(
                EMAIL,
                PASSWORD,
                USERNAME
        )).thenReturn(
                optionalAccountEntity
        );

        when(entityMapper.map(
                accountEntity
        )).thenReturn(
                account
        );

        assertEquals(
                account,
                accountRepositoryAdapter.loginWith(
                        account
                )
        );
    }

    @Test
    public void shouldReturnNullUponLoginCredentialsAreIncorrect() {
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

        Optional<AccountEntity> optionalAccountEntity =
                Optional.empty();

        when(accountRepository.login(
                EMAIL,
                PASSWORD,
                USERNAME
        )).thenReturn(
                optionalAccountEntity
        );

        when(entityMapper.map(
                accountEntity
        )).thenReturn(
                account
        );

        assertEquals(
                null,
                accountRepositoryAdapter.loginWith(
                        account
                )
        );
    }
}