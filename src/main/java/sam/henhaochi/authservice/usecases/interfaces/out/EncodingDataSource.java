package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.CheckPasswordRequest;
import sam.henhaochi.authservice.usecases.models.out.requests.EncodePasswordRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.CheckPasswordResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.EncodePasswordResponse;

public interface EncodingDataSource {
    EncodePasswordResponse encodePassword(
            final EncodePasswordRequest encodePasswordRequest
    );

    CheckPasswordResponse isPasswordCorrect(
            final CheckPasswordRequest checkPasswordRequest
    );
}
