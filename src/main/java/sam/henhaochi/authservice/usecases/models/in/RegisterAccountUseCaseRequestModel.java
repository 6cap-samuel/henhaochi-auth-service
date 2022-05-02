package sam.henhaochi.authservice.usecases.models.in;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterAccountUseCaseRequestModel {
    private String username;
    private String password;
    private String email;

    public RegisterAccountUseCaseRequestModel encodePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static RegisterAccountUseCaseRequestModel newInstance(
                final String username,
                final String password,
                final String email
        ) {
            return RegisterAccountUseCaseRequestModel.of(
                    username,
                    password,
                    email
            );
        }
    }
}
