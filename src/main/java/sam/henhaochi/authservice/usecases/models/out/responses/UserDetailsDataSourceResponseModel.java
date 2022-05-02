package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.AccountCreationStatus;

@Data
@Builder
public class UserDetailsDataSourceResponseModel implements
        RegisterResponse {

    private final AccountCreationStatus accountCreationStatus;

    @Override
    public AccountCreationStatus getAccountCreationStatus() {
        return this.accountCreationStatus;
    }

    @Override
    public boolean isUnverified() {
        return accountCreationStatus.equals(AccountCreationStatus.UNVERIFIED);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static RegisterResponse newRegisterResponse(
                 final AccountCreationStatus accountCreationStatus
         ) {
             return UserDetailsDataSourceResponseModel.builder()
                     .accountCreationStatus(accountCreationStatus)
                     .build();
         }
    }
}
