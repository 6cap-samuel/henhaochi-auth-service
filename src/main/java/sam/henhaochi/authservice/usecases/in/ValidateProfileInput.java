package sam.henhaochi.authservice.usecases.in;

import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.exceptions.EmptyResult;

public interface ValidateProfileInput {
    Account with(
            String token
    ) throws EmptyResult;
}
