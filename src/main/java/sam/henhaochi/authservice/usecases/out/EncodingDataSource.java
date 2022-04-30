package sam.henhaochi.authservice.usecases.out;

import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;

public interface EncodingDataSource {
    RegisterAccountUseCaseModel encodePassword(
            RegisterAccountUseCaseModel registerAccountUseCaseModel
    );
}
