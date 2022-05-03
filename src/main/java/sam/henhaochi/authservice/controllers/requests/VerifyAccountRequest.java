package sam.henhaochi.authservice.controllers.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import sam.henhaochi.authservice.usecases.models.in.requests.VerifyAccountUseCaseRequest;

@Data
public class VerifyAccountRequest {
    private String username;
    private String otp;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static VerifyAccountUseCaseRequest mapToVerifyAccountUseCaseRequest(
                 final VerifyAccountRequest verifyAccountRequest
        ) {
            return VerifyAccountUseCaseRequest.Factory.newInstance(
                    verifyAccountRequest.getUsername(),
                    verifyAccountRequest.getOtp()
            );
        }
    }
}
