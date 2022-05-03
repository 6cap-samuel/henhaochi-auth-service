package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class EncodingDataSourceResponseModel implements
        CheckPasswordResponse,
        EncodePasswordResponse {

    private final boolean isPasswordValid;
    private final String encodedPassword;

    @Override
    public boolean isPasswordValid() {
        return this.isPasswordValid;
    }

    @Override
    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {

        public static EncodePasswordResponse newEncodePasswordResponse(
                final String encodedPassword
        ) {
            return EncodingDataSourceResponseModel.builder()
                    .encodedPassword(encodedPassword)
                    .build();
        }

        public static CheckPasswordResponse newPasswordResponse(
                final boolean isPasswordValid
        ) {
            return EncodingDataSourceResponseModel.builder()
                    .isPasswordValid(isPasswordValid)
                    .build();
        }
    }
}
