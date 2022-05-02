package sam.henhaochi.authservice.entities;

import lombok.*;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

@Data
@AllArgsConstructor(staticName = "of")
public class UserDetails {

    private String username;
    private String password;
    private String email;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static UserDetails newInstance(
                 final String username,
                 final String password,
                 final String email
         ) {
              return UserDetails.of(
                      username,
                      password,
                      email
              );
         }
    }
}
