package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.LoginAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.models.out.LoginAccountUseCaseResponseModel;

public interface LoginAccountInput {
    LoginAccountUseCaseResponseModel with(
            final LoginAccountUseCaseRequestModel loginAccountUseCaseRequestModel
    );
}
