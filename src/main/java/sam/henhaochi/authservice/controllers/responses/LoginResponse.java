package sam.henhaochi.authservice.controllers.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    public String token;
}