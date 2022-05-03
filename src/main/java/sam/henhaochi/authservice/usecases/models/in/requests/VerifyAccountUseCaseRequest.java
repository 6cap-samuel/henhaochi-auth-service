package sam.henhaochi.authservice.usecases.models.in.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class VerifyAccountUseCaseRequest {
    private final String username;
    private final String otp;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory{
        public static VerifyAccountUseCaseRequest newInstance(
                final String username,
                final String otp
        ) {
             return VerifyAccountUseCaseRequest.of(
                     username,
                     otp
             );
        }
    }
}
