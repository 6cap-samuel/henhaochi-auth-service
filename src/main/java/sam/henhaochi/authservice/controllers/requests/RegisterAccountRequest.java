package sam.henhaochi.authservice.controllers.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.usecases.models.in.requests.RegisterAccountUseCaseRequest;

@Data
public class RegisterAccountRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
         public static RegisterAccountUseCaseRequest mapToRegisterUseCase(
                 RegisterAccountRequest registerAccountRequest
         ) {
             return RegisterAccountUseCaseRequest.Factory.newInstance(
                     registerAccountRequest.getUsername(),
                     registerAccountRequest.getPassword(),
                     registerAccountRequest.getEmail(),
                     registerAccountRequest.getPhoneNumber()
             );
         }
    }
}
