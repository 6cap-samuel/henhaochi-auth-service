package sam.henhaochi.authservice.usecases.out;

import sam.henhaochi.authservice.entities.Account;

import java.security.NoSuchAlgorithmException;

public interface TokenDataSource {
    String generateToken(
            final Account account
    ) throws NoSuchAlgorithmException;

    Account isTokenValid(
            final String token
    );
}
