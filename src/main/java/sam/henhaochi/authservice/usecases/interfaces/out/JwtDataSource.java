package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.GenerateJwtRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.GenerateJwtResponse;

public interface JwtDataSource {
     GenerateJwtResponse generateJwt(
             final GenerateJwtRequest generateJwtRequest
     );
}
