package sam.henhaochi.authservice.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sam.henhaochi.authservice.usecases.models.in.LoginAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;

@Component
@Slf4j
@AllArgsConstructor
public class PasswordEncodingAdapter implements EncodingDataSource {

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterAccountUseCaseRequestModel encodePassword(
            RegisterAccountUseCaseRequestModel registerAccountUseCaseRequestModel
    ) {
        return registerAccountUseCaseRequestModel.encodePassword(
                passwordEncoder.encode(
                        registerAccountUseCaseRequestModel.getPassword()
                )
        );
    }

    @Override
    public boolean isPasswordCorrect(
            LoginAccountUseCaseRequestModel loginAccountUseCaseRequestModel,
            UserDetails userDetails
    ) {
        return passwordEncoder.matches(
                loginAccountUseCaseRequestModel.getPassword(),
                userDetails.getPassword()
        );
    }
}
