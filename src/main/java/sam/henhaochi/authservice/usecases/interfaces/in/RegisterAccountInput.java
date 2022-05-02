package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

public interface RegisterAccountInput {
    AccountCreationStatus create(
            final RegisterAccountUseCaseRequestModel registerAccountModel
    );
}
