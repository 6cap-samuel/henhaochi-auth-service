package sam.henhaochi.authservice.controllers.responses;

import lombok.Builder;
import lombok.Data;
import sam.henhaochi.authservice.constants.Role;

@Data
@Builder
public class TokenResponse {
    public Role role;
}
