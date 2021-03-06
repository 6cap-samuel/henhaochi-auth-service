package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.usecases.interfaces.in.RegisterAccountInput;
import sam.henhaochi.authservice.usecases.interfaces.out.OtpDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.SmsDataSource;
import sam.henhaochi.authservice.usecases.models.in.requests.RegisterAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.UserDetailsDataSource;
import sam.henhaochi.authservice.usecases.models.in.responses.RegisterAccountUseCaseResponse;
import sam.henhaochi.authservice.usecases.models.out.requests.EncodingDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.requests.OtpDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.requests.SmsDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.requests.UserDetailsDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.responses.EncodePasswordResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateOtpResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.RegisterResponse;

@Service
@AllArgsConstructor
public class RegisterAccountUseCase
        implements RegisterAccountInput {

    private final UserDetailsDataSource userDetailsDataSource;
    private final EncodingDataSource encodingDataSource;
    private final OtpDataSource otpDataSource;
    private final SmsDataSource smsDataSource;

    @Override
    public RegisterAccountUseCaseResponse create(
            final RegisterAccountUseCaseRequest registerAccountModel
    ) {
        EncodePasswordResponse encodePasswordResponse =
                encodingDataSource.encodePassword(
                        EncodingDataSourceRequestModel.Factory.newEncodePasswordInstance(
                                registerAccountModel.getPassword()
                        )
                );

        RegisterResponse registerResponse = userDetailsDataSource.register(
                UserDetailsDataSourceRequestModel.Factory.newRegisterRequest(
                        registerAccountModel.getUsername(),
                        registerAccountModel.getEmail(),
                        encodePasswordResponse.getEncodedPassword()
                )
        );

        if (registerResponse.isUnverified()) {
             GenerateOtpResponse otpResponse = otpDataSource.generateOtp(
                     OtpDataSourceRequestModel.Factory.newGenerateOtpRequest(
                            registerAccountModel.getUsername()
                    )
            );

             if (otpResponse.isOtpCreationStatusSuccess()) {
                 smsDataSource.sendSms(
                         SmsDataSourceRequestModel.Factory.newSendSmsRequest(
                                 registerAccountModel.getPhoneNumber(),
                                 String.format("Your OTP is: %s", otpResponse.getOtpNumber())
                         )
                 );
             }
        }

        return RegisterAccountUseCaseResponse.Factory
                .newInstance(
                        registerResponse.getAccountCreationStatus()
                );
    }
}
