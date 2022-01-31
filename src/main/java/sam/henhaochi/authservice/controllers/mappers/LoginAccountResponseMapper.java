package sam.henhaochi.authservice.controllers.mappers;

import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.controllers.responses.LoginResponse;
import sam.henhaochi.authservice.entities.Account;

@Service
public class LoginAccountResponseMapper {
    public LoginResponse map(
            Account account
    ){
        return LoginResponse.builder()
                .token(account.getToken())
                .build();
    }
}
