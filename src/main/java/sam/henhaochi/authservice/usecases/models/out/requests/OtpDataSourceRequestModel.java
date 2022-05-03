package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class OtpDataSourceRequestModel implements
        GenerateOtpRequest,
        VerifyOtpRequest {

    private final String username;
    private final String otp;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getOtp() {
        return this.otp;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static GenerateOtpRequest newGenerateOtpRequest(
                 final String username
         ) {
             return OtpDataSourceRequestModel.builder()
                     .username(username)
                     .build();
         }

        public static VerifyOtpRequest newVerifyOtpRequest(
                final String username,
                final String otp
        ) {
            return OtpDataSourceRequestModel.builder()
                    .username(username)
                    .otp(otp)
                    .build();
        }
    }
}
