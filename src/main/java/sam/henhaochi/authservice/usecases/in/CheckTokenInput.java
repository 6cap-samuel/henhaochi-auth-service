package sam.henhaochi.authservice.usecases.in;

import sam.henhaochi.authservice.entities.Account;

public interface CheckTokenInput {
    Account check(
            String token
    );
}
