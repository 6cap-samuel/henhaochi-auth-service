package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.usecases.interfaces.in.RegisterAccountInput;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.UserDetailsDataSource;
import sam.henhaochi.authservice.usecases.models.out.RegisterAccountUseCaseResponseModel;

@Service
@AllArgsConstructor
public class RegisterAccountUseCase
        implements RegisterAccountInput {

    private final UserDetailsDataSource userDetailsDataSource;
    private final EncodingDataSource encodingDataSource;

    @Override
    public RegisterAccountUseCaseResponseModel create(
            final RegisterAccountUseCaseRequestModel registerAccountModel
    ) {
        return RegisterAccountUseCaseResponseModel.Factory.newInstance(
                userDetailsDataSource.register(
                        encodingDataSource.encodePassword(
                                registerAccountModel
                        )
                )
        );
    }
}
