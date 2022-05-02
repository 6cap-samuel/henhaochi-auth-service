package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.usecases.interfaces.in.LoginAccountInput;
import sam.henhaochi.authservice.usecases.models.in.LoginAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.models.out.LoginAccountUseCaseResponseModel;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.JwtDataSource;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LoginAccountUseCase
        implements LoginAccountInput {

    final UserDetailsService userDetailsService;
    final EncodingDataSource encodingDataSource;
    final JwtDataSource jwtDataSource;

    @Override
    @Transactional
    public LoginAccountUseCaseResponseModel with(
            final LoginAccountUseCaseRequestModel loginAccountUseCaseRequestModel
    ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                loginAccountUseCaseRequestModel.getUsername()
        );

        if (encodingDataSource.isPasswordCorrect(loginAccountUseCaseRequestModel, userDetails)) {
            return LoginAccountUseCaseResponseModel.Factory.success(
                    jwtDataSource.generateJwt(userDetails)
            );
        }

        return LoginAccountUseCaseResponseModel.Factory.fail();
    }
}
