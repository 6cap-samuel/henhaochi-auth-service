package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class OtpDataSourceRequestModel implements
        GenerateOtpRequest {

    private final String username;

    @Override
    public String getUsername() {
        return this.username;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static GenerateOtpRequest newGenerateOtpRequest(
                 final String username
         ) {
             return OtpDataSourceRequestModel.builder()
                     .username(username)
                     .build();
         }
    }
}
