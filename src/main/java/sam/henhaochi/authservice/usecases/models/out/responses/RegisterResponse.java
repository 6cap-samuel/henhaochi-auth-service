package sam.henhaochi.authservice.usecases.models.out.responses;

import sam.henhaochi.authservice.constants.AccountCreationStatus;

public interface RegisterResponse {
    AccountCreationStatus getAccountCreationStatus();
    boolean isUnverified();
}
