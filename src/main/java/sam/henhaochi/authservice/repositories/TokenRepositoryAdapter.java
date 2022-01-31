package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.repositories.entities.AccountEntity;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;
import sam.henhaochi.authservice.repositories.mappers.AccountEntityMapper;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import java.security.NoSuchAlgorithmException;

@Repository
@AllArgsConstructor
public class TokenRepositoryAdapter
        implements TokenDataSource {

    final TokenRepository tokenRepository;
    final AccountEntityMapper entityMapper;

    @Override
    public String generateToken(Account account)
            throws NoSuchAlgorithmException {
        AccountEntity accountEntity =
                entityMapper.map(account);

        TokenEntity tokenEntity = TokenEntity.newInstance(
                accountEntity
        );

        tokenRepository.save(tokenEntity);

        return tokenEntity.getTokenString();
    }
}