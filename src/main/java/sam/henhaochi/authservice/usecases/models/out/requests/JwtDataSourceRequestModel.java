package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
public class JwtDataSourceRequestModel implements
        GenerateJwtRequest {

    private final UserDetails userDetails;

    @Override
    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static GenerateJwtRequest newGenerateJwtRequest(
                final UserDetails userDetails
        ) {
            return JwtDataSourceRequestModel.builder()
                    .userDetails(userDetails)
                    .build();
        }
    }
}
