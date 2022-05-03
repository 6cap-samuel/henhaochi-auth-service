package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.GenerateOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.requests.VerifyOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateOtpResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.VerifyOtpResponse;

public interface OtpDataSource {
    GenerateOtpResponse generateOtp(
            final GenerateOtpRequest generateOtpRequest
    );

    VerifyOtpResponse verifyOtp(
             final VerifyOtpRequest verifyOtpRequest
    );
}
