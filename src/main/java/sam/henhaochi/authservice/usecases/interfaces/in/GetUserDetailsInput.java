package sam.henhaochi.authservice.usecases.interfaces.in;

import sam.henhaochi.authservice.usecases.models.in.requests.GetUserDetailsUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.GetUserDetailsUseCaseResponse;

public interface GetUserDetailsInput {
    GetUserDetailsUseCaseResponse get(
            final GetUserDetailsUseCaseRequest request
    );
}
