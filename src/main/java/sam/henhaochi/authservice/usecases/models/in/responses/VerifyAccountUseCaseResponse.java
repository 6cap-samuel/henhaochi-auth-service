package sam.henhaochi.authservice.usecases.models.in.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.AccountCreationStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class VerifyAccountUseCaseResponse {

    private AccountCreationStatus accountCreationStatus;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static VerifyAccountUseCaseResponse newInstance(
                 final AccountCreationStatus accountCreationStatus
         )  {
              return VerifyAccountUseCaseResponse.of(
                      accountCreationStatus
              );
         }
    }
}
