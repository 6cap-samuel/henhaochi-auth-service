package sam.henhaochi.authservice.usecases.interfaces.out;

import org.springframework.security.core.userdetails.UserDetails;
import sam.henhaochi.authservice.usecases.models.in.LoginAccountUseCaseRequestModel;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

public interface EncodingDataSource {
    RegisterAccountUseCaseRequestModel encodePassword(
            RegisterAccountUseCaseRequestModel registerAccountUseCaseRequestModel
    );
    boolean isPasswordCorrect(
            LoginAccountUseCaseRequestModel loginAccountUseCaseRequestModel,
            UserDetails userDetails
    );
}
