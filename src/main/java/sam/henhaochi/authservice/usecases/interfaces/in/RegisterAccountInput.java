package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.requests.RegisterAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.RegisterAccountUseCaseResponse;

public interface RegisterAccountInput {
    RegisterAccountUseCaseResponse create(
            final RegisterAccountUseCaseRequest registerAccountModel
    );
}
