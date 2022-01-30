package sam.henhaochi.authservice.controllers.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAccountRequest {
    public String username;
    public String password;
    public String email;
}
