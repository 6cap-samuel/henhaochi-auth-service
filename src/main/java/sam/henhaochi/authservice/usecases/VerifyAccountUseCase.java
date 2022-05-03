package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.usecases.interfaces.in.VerifyAccountInput;
import sam.henhaochi.authservice.usecases.interfaces.out.OtpDataSource;
import sam.henhaochi.authservice.usecases.models.in.requests.VerifyAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.VerifyAccountUseCaseResponse;
import sam.henhaochi.authservice.usecases.models.out.requests.OtpDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.responses.VerifyOtpResponse;

@Service
@AllArgsConstructor
public class VerifyAccountUseCase implements
        VerifyAccountInput {

    private final OtpDataSource otpDataSource;

    @Override
    public VerifyAccountUseCaseResponse verify(
            final VerifyAccountUseCaseRequest verifyAccountUseCaseRequest
    ) {
        VerifyOtpResponse verifyOtpResponse = otpDataSource.verifyOtp(
                OtpDataSourceRequestModel.Factory.newVerifyOtpRequest(
                        verifyAccountUseCaseRequest.getUsername(),
                        verifyAccountUseCaseRequest.getOtp()
                )
        );

        return VerifyAccountUseCaseResponse.Factory.newInstance(
                verifyOtpResponse.getAccountCreationStatus()
        );
    }
}
