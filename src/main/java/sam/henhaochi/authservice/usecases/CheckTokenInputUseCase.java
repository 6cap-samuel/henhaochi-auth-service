package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.usecases.in.CheckTokenInput;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

@Service
@AllArgsConstructor
public class CheckTokenInputUseCase
        implements CheckTokenInput {

    final TokenDataSource tokenDataSource;

    @Override
    public Account check(
            final String token
    ) {
        return tokenDataSource.isTokenValid(
                token
        );
    }
}
