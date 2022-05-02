package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.RegisterRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.RegisterResponse;

public interface UserDetailsDataSource {
    RegisterResponse register(
            final RegisterRequest registerRequest
    );
}
