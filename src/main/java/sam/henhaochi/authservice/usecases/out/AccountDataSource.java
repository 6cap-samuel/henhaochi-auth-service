package sam.henhaochi.authservice.usecases.out;

import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;

public interface AccountDataSource {
    Account loginWith(
            final Account account
    );
    AccountCreationStatus registerWith(
            final Account account
    );
}
