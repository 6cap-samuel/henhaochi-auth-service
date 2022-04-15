package sam.henhaochi.authservice.controllers.mappers;

import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.controllers.responses.LoginResponse;
import sam.henhaochi.authservice.controllers.responses.ValidateResponse;
import sam.henhaochi.authservice.entities.Account;

@Service
public class LoginAccountResponseMapper {
    public LoginResponse mapToLoginResponse(
            Account account
    ){
        return LoginResponse.builder()
                .token(account.getToken())
                .role(account.getRole())
                .build();
    }

    public ValidateResponse mapToValidateResponse(
            Account account
    ) {
        return ValidateResponse.builder()
                .username(account.getUsername())
                .role(account.getRole())
                .build();
    }

}
