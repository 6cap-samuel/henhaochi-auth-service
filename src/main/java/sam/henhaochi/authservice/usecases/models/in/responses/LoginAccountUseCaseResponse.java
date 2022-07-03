package sam.henhaochi.authservice.usecases.models.in.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;
import sam.henhaochi.authservice.constants.LoginStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class LoginAccountUseCaseResponse {
    private final LoginStatus loginStatus;

    @Nullable
    private final String token;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {

        public static LoginAccountUseCaseResponse success (
                final String token
        ) {
            return LoginAccountUseCaseResponse.of(
                    LoginStatus.YES,
                    token
            );
        }

        public static LoginAccountUseCaseResponse unverified() {
            return LoginAccountUseCaseResponse.of(
                    LoginStatus.NO,
                    null
            );
        }

        public static LoginAccountUseCaseResponse fail() {
            return LoginAccountUseCaseResponse.of(
                    LoginStatus.NO,
                    null
            );
        }
    }
}
