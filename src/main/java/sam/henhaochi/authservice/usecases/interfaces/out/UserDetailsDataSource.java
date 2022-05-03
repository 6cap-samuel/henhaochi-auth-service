package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.models.out.requests.RegisterRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.RegisterResponse;

import java.util.Optional;

public interface UserDetailsDataSource {
    RegisterResponse register(
            final RegisterRequest registerRequest
    );

    Optional<UserDetailsEntity> findByUsername(
            final String username
    );

    void enableAccount(
            final String username
    );
}
