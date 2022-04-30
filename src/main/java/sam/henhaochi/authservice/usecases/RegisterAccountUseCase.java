package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.usecases.in.RegisterAccountInput;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;
import sam.henhaochi.authservice.usecases.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.out.UserDetailsDataSource;

@Service
@AllArgsConstructor
public class RegisterAccountUseCase
        implements RegisterAccountInput {

    final UserDetailsDataSource userDetailsDataSource;
    final EncodingDataSource encodingDataSource;

    @Override
    public AccountCreationStatus with(
            final RegisterAccountUseCaseModel registerAccountModel
    ) {
        return userDetailsDataSource.register(
                encodingDataSource.encodePassword(
                        registerAccountModel
                )
        );
    }
}
