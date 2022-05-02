package sam.henhaochi.authservice.controllers.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

@Data
@Builder
public class RegisterAccountRequest {
    private String username;
    private String password;
    private String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
         public static RegisterAccountUseCaseRequestModel mapToRegisterUseCase(
                 RegisterAccountRequest registerAccountRequest
         ) {
             return RegisterAccountUseCaseRequestModel.Factory.newInstance(
                     registerAccountRequest.getUsername(),
                     registerAccountRequest.getPassword(),
                     registerAccountRequest.getEmail()
             );
         }
    }
}
