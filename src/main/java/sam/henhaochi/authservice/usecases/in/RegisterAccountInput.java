package sam.henhaochi.authservice.usecases.in;

import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;

import java.security.NoSuchAlgorithmException;

public interface RegisterAccountInput {
    AccountCreationStatus with(
            final RegisterAccountUseCaseModel registerAccountModel
    ) throws NoSuchAlgorithmException;
}
