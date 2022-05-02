package sam.henhaochi.authservice.usecases.models.out;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import sam.henhaochi.authservice.constants.LoginStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class LoginAccountUseCaseResponseModel {
    private final LoginStatus loginStatus;
    private final String token;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static LoginAccountUseCaseResponseModel success (
                final String token
        ) {
            return LoginAccountUseCaseResponseModel.of(
                    LoginStatus.YES,
                    token
            );
        }

        public static LoginAccountUseCaseResponseModel fail() {
            return LoginAccountUseCaseResponseModel.of(
                    LoginStatus.NO,
                    null
            );
        }
    }
}
