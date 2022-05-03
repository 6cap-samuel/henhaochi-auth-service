package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class JwtDataSourceResponseModel implements
        GenerateJwtResponse {

    private final String jwtString;

    @Override
    public String getJwtString() {
        return this.jwtString;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static GenerateJwtResponse newGenerateJwtResponseInstance(
                 final String jwtString
         ) {
             return JwtDataSourceResponseModel.builder()
                     .jwtString(jwtString)
                     .build();
         }
    }
}
