package sam.henhaochi.authservice.controllers.requests;

import lombok.Builder;
import lombok.Data;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;

@Data
@Builder
public class RegisterAccountRequest {
    public String username;
    public String password;
    public String email;

    public static class Mapper {
         public static RegisterAccountUseCaseModel mapToRegisterUseCase(
                 RegisterAccountRequest registerAccountRequest
         ) {
             return RegisterAccountUseCaseModel.Factory.newInstance(
                     registerAccountRequest.getUsername(),
                     registerAccountRequest.getPassword(),
                     registerAccountRequest.getEmail()
             );
         }
    }
}
