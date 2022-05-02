package sam.henhaochi.authservice.usecases.models.out.requests;

import org.springframework.security.core.userdetails.UserDetails;

public interface GenerateJwtRequest {
    UserDetails getUserDetails();
}
