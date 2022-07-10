package sam.henhaochi.authservice.usecases.models.in.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
public class GetUserDetailsUseCaseRequest {
    private final String username;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static GetUserDetailsUseCaseRequest newInstance(
                final String username
        ) {
            return GetUserDetailsUseCaseRequest.of(
                    username
            );
        }
    }
}
