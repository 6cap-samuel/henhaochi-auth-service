package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.LoginAccountInput;
import sam.henhaochi.authservice.usecases.out.AccountDataSource;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class LoginAccountUseCase
        implements LoginAccountInput {

    final AccountDataSource accountDataSource;
    final TokenDataSource tokenDataSource;

    @Override
    @Transactional
    public Account with(
            Account account
    ) throws NoSuchAlgorithmException {
        account.encrypt();
        Account found = accountDataSource.loginWith(account);
        if (found != null){
            found.tokenize(
                    tokenDataSource.generateToken(found)
            );
        }
        return found;
    }
}
