package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserDetailsDataSourceRequestModel implements
        RegisterRequest {

    private final String username;
    private final String email;
    private final String password;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static RegisterRequest newRegisterRequest(
                 final String username,
                 final String email,
                 final String password
         ) {
              return UserDetailsDataSourceRequestModel.builder()
                      .username(username)
                      .email(email)
                      .password(password)
                      .build();
         }
    }
}
