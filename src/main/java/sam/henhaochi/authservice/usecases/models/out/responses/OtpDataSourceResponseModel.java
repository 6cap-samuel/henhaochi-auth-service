package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.constants.OtpCreationStatus;

@Data
@Builder
public class OtpDataSourceResponseModel implements
        GenerateOtpResponse,
        VerifyOtpResponse {

    private final OtpCreationStatus otpCreationStatus;
    private final AccountCreationStatus accountCreationStatus;

    private final String otpNumber;

    @Override
    public String getOtpNumber() {
        return this.otpNumber;
    }

    @Override
    public boolean isOtpCreationStatusSuccess() {
        return this.otpCreationStatus.equals(OtpCreationStatus.SUCCESS);
    }

    @Override
    public AccountCreationStatus getAccountCreationStatus() {
        return this.accountCreationStatus;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static GenerateOtpResponse newGenerateOtpResponse(
                final OtpCreationStatus otpCreationStatus,
                final String otpNumber
        ) {
             return OtpDataSourceResponseModel.builder()
                     .otpCreationStatus(otpCreationStatus)
                     .otpNumber(otpNumber)
                     .build();
        }

        public static VerifyOtpResponse newVerifyOtpResponse(
                final AccountCreationStatus accountCreationStatus
        ){
             return OtpDataSourceResponseModel.builder()
                     .accountCreationStatus(accountCreationStatus)
                     .build();
        }
    }
}
