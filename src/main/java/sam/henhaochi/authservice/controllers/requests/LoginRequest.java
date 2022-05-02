package sam.henhaochi.authservice.controllers.requests;

import lombok.*;
import sam.henhaochi.authservice.usecases.models.in.LoginAccountUseCaseRequestModel;

@Data
@AllArgsConstructor
@Builder
public class LoginRequest {
    private String username;
    private String password;
    private String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static LoginAccountUseCaseRequestModel mapToLoginRequestModel(
                final LoginRequest loginRequest
        ) {
            return LoginAccountUseCaseRequestModel.Factory.newInstance(
                    loginRequest.getUsername(),
                    loginRequest.getPassword(),
                    loginRequest.getEmail()
            );
        }
    }
}
