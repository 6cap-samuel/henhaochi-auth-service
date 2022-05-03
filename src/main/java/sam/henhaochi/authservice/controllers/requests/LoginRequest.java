package sam.henhaochi.authservice.controllers.requests;

import lombok.*;
import sam.henhaochi.authservice.usecases.models.in.requests.LoginAccountUseCaseRequest;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static LoginAccountUseCaseRequest mapToLoginRequestModel(
                final LoginRequest loginRequest
        ) {
            return LoginAccountUseCaseRequest.Factory.newInstance(
                    loginRequest.getUsername(),
                    loginRequest.getPassword(),
                    loginRequest.getEmail()
            );
        }
    }
}
