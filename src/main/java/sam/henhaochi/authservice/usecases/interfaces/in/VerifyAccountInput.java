package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.requests.VerifyAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.VerifyAccountUseCaseResponse;

public interface VerifyAccountInput {
    VerifyAccountUseCaseResponse verify (
            final VerifyAccountUseCaseRequest verifyAccountUseCaseRequest
    );
}
