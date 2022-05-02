package sam.henhaochi.authservice.usecases.models.out;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.AccountCreationStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterAccountUseCaseResponseModel {
    private final AccountCreationStatus accountCreationStatus;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static RegisterAccountUseCaseResponseModel newInstance(
                final AccountCreationStatus accountCreationStatus
        ) {
             return RegisterAccountUseCaseResponseModel.of(
                     accountCreationStatus
             );
        }
    }
}
