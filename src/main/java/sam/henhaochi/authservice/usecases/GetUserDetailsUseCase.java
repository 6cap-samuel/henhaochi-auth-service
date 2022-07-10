package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.interfaces.in.GetUserDetailsInput;
import sam.henhaochi.authservice.usecases.interfaces.out.UserDetailsDataSource;
import sam.henhaochi.authservice.usecases.models.in.requests.GetUserDetailsUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.GetUserDetailsUseCaseResponse;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserDetailsUseCase implements GetUserDetailsInput {

    private final UserDetailsDataSource userDetailsDataSource;

    @Override
    public GetUserDetailsUseCaseResponse get(
            final GetUserDetailsUseCaseRequest request
    ) {
        Optional<UserDetailsEntity> userDetailsOptional = userDetailsDataSource.findByUsername(request.getUsername());

        return userDetailsOptional.isPresent()
                ? GetUserDetailsUseCaseResponse.Factory.newInstance(userDetailsOptional.get())
                : GetUserDetailsUseCaseResponse.Factory.empty();
    }
}
