package sam.henhaochi.authservice.usecases.models.in.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterAccountUseCaseRequest {
    private final String username;
    private final String password;
    private final String email;
    private final String phoneNumber;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static RegisterAccountUseCaseRequest newInstance(
                final String username,
                final String password,
                final String email,
                final String phoneNumber
        ) {
            return RegisterAccountUseCaseRequest.of(
                    username,
                    password,
                    email,
                    phoneNumber
            );
        }
    }
}
