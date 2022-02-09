package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.RegisterAccountInput;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class RegisterAccountUseCase
        implements RegisterAccountInput {

    final AccountDataSource accountDataSource;

    @Override
    public AccountCreationStatus with(
            final Account account
    ) throws NoSuchAlgorithmException {
        account.encrypt();
        return accountDataSource.registerWith(account);
    }
}
