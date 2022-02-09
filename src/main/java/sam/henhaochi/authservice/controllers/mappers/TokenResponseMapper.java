package sam.henhaochi.authservice.controllers.mappers;

import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.controllers.responses.TokenResponse;
import sam.henhaochi.authservice.entities.Account;

@Service
public class TokenResponseMapper {
    public TokenResponse map(
            final Account account
    ) {
        return TokenResponse.builder()
                .role(account.getRole())
                .build();
    }
}
