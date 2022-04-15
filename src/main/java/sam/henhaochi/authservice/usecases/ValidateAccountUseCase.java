package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.exceptions.EmptyResult;
import sam.henhaochi.authservice.usecases.in.ValidateProfileInput;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

@Service
@AllArgsConstructor
public class ValidateAccountUseCase implements ValidateProfileInput {

    final TokenDataSource tokenDataSource;

    @Override
    public Account with(String token)
            throws EmptyResult {
        return tokenDataSource.getProfileFromToken(token);
    }
}
