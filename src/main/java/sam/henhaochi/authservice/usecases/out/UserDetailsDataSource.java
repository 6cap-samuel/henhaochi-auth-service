package sam.henhaochi.authservice.usecases.out;

import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;

public interface UserDetailsDataSource {
    AccountCreationStatus register(
            final RegisterAccountUseCaseModel registerAccountUseCaseModel
    );
}
