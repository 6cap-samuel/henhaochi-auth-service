package sam.henhaochi.authservice.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.CheckPasswordRequest;
import sam.henhaochi.authservice.usecases.models.out.requests.EncodePasswordRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.CheckPasswordResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.EncodePasswordResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.EncodingDataSourceResponseModel;

@Component
@Slf4j
@AllArgsConstructor
public class PasswordEncodingAdapter implements EncodingDataSource {

    private final PasswordEncoder passwordEncoder;

    @Override
    public EncodePasswordResponse encodePassword(
            final EncodePasswordRequest encodePasswordRequest
    ) {
        return EncodingDataSourceResponseModel.Factory.newEncodePasswordResponse(
                passwordEncoder.encode(
                        encodePasswordRequest.getPasswordToEncode()
                )
        );
    }

    @Override
    public CheckPasswordResponse isPasswordCorrect(
            final CheckPasswordRequest checkPasswordRequest
    ) {
        return EncodingDataSourceResponseModel.Factory.newPasswordResponse(
                passwordEncoder.matches(
                        checkPasswordRequest.getInputPassword(),
                        checkPasswordRequest.getHashedPassword()
                )
        );
    }
}
