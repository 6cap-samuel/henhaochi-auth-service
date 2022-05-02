package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.requests.LoginAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.LoginAccountUseCaseResponse;

public interface LoginAccountInput {
    LoginAccountUseCaseResponse with(
            final LoginAccountUseCaseRequest loginAccountUseCaseRequestModel
    );
}
