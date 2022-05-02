package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.GenerateOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateOtpResponse;

public interface OtpDataSource {
    GenerateOtpResponse generateOtp(
            final GenerateOtpRequest generateOtpRequest
    );
}
