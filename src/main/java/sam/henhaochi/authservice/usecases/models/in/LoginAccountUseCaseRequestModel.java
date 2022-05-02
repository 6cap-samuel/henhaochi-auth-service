package sam.henhaochi.authservice.usecases.models.in;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class LoginAccountUseCaseRequestModel {
    private String username;
    private String password;
    private String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static LoginAccountUseCaseRequestModel newInstance(
                 final String username,
                 final String password,
                 final String email
         ) {
             return LoginAccountUseCaseRequestModel.of(
                     username,
                     password,
                     email
             );
         }
    }
}
