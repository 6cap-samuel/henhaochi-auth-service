package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.exceptions.EmptyResult;
import sam.henhaochi.authservice.repositories.entities.AccountEntity;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;
import sam.henhaochi.authservice.repositories.mappers.AccountEntityMapper;
import sam.henhaochi.authservice.usecases.out.TokenDataSource;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Optional;

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

    @Override
    public boolean isTokenValid(String token) {
        return tokenRepository.existsByTokenStringEqualsAndExpirationDateIsGreaterThan(
                token,
                new Timestamp(
                        System.currentTimeMillis()
                )
        );
    }

    @Override
    public Account getProfileFromToken(String token)
            throws EmptyResult {

        Optional<TokenEntity> foundToken =
                tokenRepository.findById(token);

        if (foundToken.isEmpty()) {
            throw new EmptyResult("No such token");
        }

        return entityMapper.map(
                foundToken.get()
                        .getAccount()
        );
    }
}
