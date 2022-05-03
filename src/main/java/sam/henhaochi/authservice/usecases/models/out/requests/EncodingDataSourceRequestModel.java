package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class EncodingDataSourceRequestModel implements
        CheckPasswordRequest,
        EncodePasswordRequest {

    private final String inputPassword;
    private final String userDetailsPassword;

    @Override
    public String getInputPassword() {
        return this.inputPassword;
    }

    @Override
    public String getPasswordToEncode() {
        return this.inputPassword;
    }

    @Override
    public String getHashedPassword() {
        return this.userDetailsPassword;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static EncodePasswordRequest newEncodePasswordInstance(
                final String inputPassword
        ) {
            return EncodingDataSourceRequestModel.builder()
                    .inputPassword(inputPassword)
                    .build();
        }

        public static CheckPasswordRequest newPasswordRequestInstance(
                final String inputPassword,
                final String userDetailsPassword
        ) {
             return EncodingDataSourceRequestModel.builder()
                     .inputPassword(inputPassword)
                     .userDetailsPassword(userDetailsPassword)
                     .build();
        }
    }
}
