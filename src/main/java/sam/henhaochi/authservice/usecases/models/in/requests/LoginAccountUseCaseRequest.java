package sam.henhaochi.authservice.usecases.models.in.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class LoginAccountUseCaseRequest {
    private final String username;
    private final String password;
    private final String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static LoginAccountUseCaseRequest newInstance(
                 final String username,
                 final String password,
                 final String email
         ) {
             return LoginAccountUseCaseRequest.of(
                     username,
                     password,
                     email
             );
         }
    }
}
