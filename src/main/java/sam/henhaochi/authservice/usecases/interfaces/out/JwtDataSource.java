package sam.henhaochi.authservice.usecases.interfaces.out;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtDataSource {
     String generateJwt(final UserDetails userDetails);
}
