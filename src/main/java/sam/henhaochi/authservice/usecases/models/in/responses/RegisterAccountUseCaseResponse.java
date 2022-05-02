package sam.henhaochi.authservice.usecases.models.in.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.AccountCreationStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterAccountUseCaseResponse {
    private final AccountCreationStatus accountCreationStatus;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static RegisterAccountUseCaseResponse newInstance(
                final AccountCreationStatus accountCreationStatus
        ) {
             return RegisterAccountUseCaseResponse.of(
                     accountCreationStatus
             );
        }
    }
}
