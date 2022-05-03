package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.constants.OtpCreationStatus;
import sam.henhaochi.authservice.repositories.entities.OtpEntity;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.interfaces.out.OtpDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.UserDetailsDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.GenerateOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.requests.VerifyOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateOtpResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.OtpDataSourceResponseModel;
import sam.henhaochi.authservice.usecases.models.out.responses.VerifyOtpResponse;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Repository
@AllArgsConstructor
@Slf4j
public class OtpAdapter implements
        OtpDataSource {

    private final OtpRepository otpRepository;
    private final UserDetailsDataSource userDetailsDataSource;
    private final Random random;

    @Override
    public GenerateOtpResponse generateOtp(
            final GenerateOtpRequest generateOtpRequest
    ) {
        Optional<UserDetailsEntity> userDetails =
                userDetailsDataSource.findByUsername(
                        generateOtpRequest.getUsername()
                );

        return userDetails.isPresent()
                ? this.generateOtpResponseWhenAccountExist(userDetails.get())
                : this.generateOtpResponseWHenAccountDoesNotExist();
    }

    @Override
    @Transactional
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        return otpRepository.existsByCodeAndUserDetails_Username(
                verifyOtpRequest.getOtp(),
                verifyOtpRequest.getUsername())
                ? this.enableAccount(verifyOtpRequest.getUsername())
                : OtpDataSourceResponseModel.Factory.newVerifyOtpResponse(AccountCreationStatus.WRONG_OTP);
    }

    private VerifyOtpResponse enableAccount(
            final String username
    ) {
        this.userDetailsDataSource.enableAccount(username);
        this.deleteOtpByUsername(username);
        return OtpDataSourceResponseModel.Factory
                .newVerifyOtpResponse(AccountCreationStatus.VERIFIED);
    }

    private void deleteOtpByUsername(
            final String username
    ) {
        this.otpRepository.deleteAllByUserDetails_Username(username);
    }

    private GenerateOtpResponse generateOtpResponseWhenAccountExist(
            final UserDetailsEntity userDetailsEntity
    ) {
        String otpNumber = String.valueOf(
                random.nextInt(100000)
        );

        this.otpRepository.save(
                OtpEntity.Factory.newInstance(
                        otpNumber,
                        userDetailsEntity
                )
        );

        return OtpDataSourceResponseModel.Factory
                .newGenerateOtpResponse(
                        OtpCreationStatus.SUCCESS,
                        otpNumber
                );
    }

    private GenerateOtpResponse generateOtpResponseWHenAccountDoesNotExist() {
        return OtpDataSourceResponseModel.Factory.newGenerateOtpResponse(
                OtpCreationStatus.ACCOUNT_NOT_EXIST,
                null
        );
    }
}
