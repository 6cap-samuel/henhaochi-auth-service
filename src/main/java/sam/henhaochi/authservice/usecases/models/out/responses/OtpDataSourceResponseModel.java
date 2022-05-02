package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.constants.OtpCreationStatus;

@Data
@Builder
public class OtpDataSourceResponseModel
        implements GenerateOtpResponse {

    private final OtpCreationStatus otpCreationStatus;

    @Override
    public OtpCreationStatus getOtpCreationStatus() {
        return this.otpCreationStatus;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static GenerateOtpResponse newGenerateOtpResponse(
                final OtpCreationStatus otpCreationStatus
        ) {
             return OtpDataSourceResponseModel.builder()
                     .otpCreationStatus(otpCreationStatus)
                     .build();
        }
    }
}
