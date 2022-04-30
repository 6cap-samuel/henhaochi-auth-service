package sam.henhaochi.authservice.configurations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;
import sam.henhaochi.authservice.usecases.out.EncodingDataSource;

@Component
@AllArgsConstructor
public class PasswordEncodingAdapter implements EncodingDataSource {

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterAccountUseCaseModel encodePassword(
            RegisterAccountUseCaseModel registerAccountUseCaseModel
    ) {
        return registerAccountUseCaseModel.encodePassword(
                passwordEncoder.encode(
                        registerAccountUseCaseModel.getPassword()
                )
        );
    }
}
