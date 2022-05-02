package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.models.out.RegisterAccountUseCaseResponseModel;

public interface RegisterAccountInput {
    RegisterAccountUseCaseResponseModel create(
            final RegisterAccountUseCaseRequestModel registerAccountModel
    );
}
