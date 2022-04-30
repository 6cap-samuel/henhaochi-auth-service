package sam.henhaochi.authservice.usecases.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterAccountUseCaseModel {
    private String username;
    private String password;
    private String email;

    public RegisterAccountUseCaseModel encodePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static RegisterAccountUseCaseModel newInstance(
                final String username,
                final String password,
                final String email
        ) {
            return RegisterAccountUseCaseModel.of(
                    username,
                    password,
                    email
            );
        }
    }
}
