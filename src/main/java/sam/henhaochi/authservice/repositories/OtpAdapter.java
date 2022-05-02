package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.constants.OtpCreationStatus;
import sam.henhaochi.authservice.repositories.entities.OtpEntity;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.interfaces.out.OtpDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.GenerateOtpRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateOtpResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.OtpDataSourceResponseModel;

import java.util.Optional;
import java.util.Random;

@Repository
@AllArgsConstructor
@Slf4j
public class OtpAdapter implements
        OtpDataSource {

    private final OtpRepository otpRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final Random random;

    @Override
    public GenerateOtpResponse generateOtp(
            final GenerateOtpRequest generateOtpRequest
    ) {
        Optional<UserDetailsEntity> userDetails =
                userDetailsRepository.findById(generateOtpRequest.getUsername());

        return userDetails.isPresent()
                ? this.generateOtpResponseWhenAccountExist(userDetails.get())
                : this.generateOtpResponseWHenAccountDoesNotExist();
    }

    private GenerateOtpResponse generateOtpResponseWhenAccountExist(
            final UserDetailsEntity userDetailsEntity
    ) {
        this.otpRepository.save(
                OtpEntity.Factory.newInstance(
                        String.valueOf(random.nextInt(100000)),
                        userDetailsEntity
                )
        );

        return OtpDataSourceResponseModel.Factory.newGenerateOtpResponse(
                OtpCreationStatus.SUCCESS
        );
    }

    private GenerateOtpResponse generateOtpResponseWHenAccountDoesNotExist() {
        return OtpDataSourceResponseModel.Factory.newGenerateOtpResponse(
                OtpCreationStatus.ACCOUNT_NOT_EXIST
        );
    }
}
