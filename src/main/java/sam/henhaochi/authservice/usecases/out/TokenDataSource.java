package sam.henhaochi.authservice.usecases.out;

import sam.henhaochi.authservice.entities.Account;

import java.security.NoSuchAlgorithmException;

public interface TokenDataSource {
    String generateToken(
            Account account
    ) throws NoSuchAlgorithmException;

    boolean isTokenValid(
            String token
    );
}
