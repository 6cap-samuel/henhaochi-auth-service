package sam.henhaochi.authservice.usecases;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sam.henhaochi.authservice.usecases.interfaces.in.LoginAccountInput;
import sam.henhaochi.authservice.usecases.models.in.requests.LoginAccountUseCaseRequest;
import sam.henhaochi.authservice.usecases.models.in.responses.LoginAccountUseCaseResponse;
import sam.henhaochi.authservice.usecases.interfaces.out.EncodingDataSource;
import sam.henhaochi.authservice.usecases.interfaces.out.JwtDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.EncodingDataSourceRequestModel;
import sam.henhaochi.authservice.usecases.models.out.requests.JwtDataSourceRequestModel;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LoginAccountUseCase
        implements LoginAccountInput {

    private final UserDetailsService userDetailsService;
    private final EncodingDataSource encodingDataSource;
    private final JwtDataSource jwtDataSource;

    @Override
    @Transactional
    public LoginAccountUseCaseResponse with(
            final LoginAccountUseCaseRequest loginAccountUseCaseRequestModel
    ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                loginAccountUseCaseRequestModel.getUsername()
        );

        if (userDetails.isEnabled()) {
             return LoginAccountUseCaseResponse.Factory.unverified();
        }

        if (encodingDataSource.isPasswordCorrect(
                EncodingDataSourceRequestModel.Factory.newPasswordRequestInstance(
                        loginAccountUseCaseRequestModel.getPassword(),
                        userDetails.getPassword()
                )).isPasswordValid()
        ) {

            return LoginAccountUseCaseResponse.Factory.success(
                    jwtDataSource.generateJwt(
                            JwtDataSourceRequestModel.Factory
                                    .newGenerateJwtRequest(userDetails)
                    ).getJwtString()
            );
        }

        return LoginAccountUseCaseResponse.Factory.fail();
    }
}
