package sam.henhaochi.authservice.controllers.responses;

import lombok.Builder;
import lombok.Data;
import sam.henhaochi.authservice.constants.Role;

@Data
@Builder
public class ValidateResponse {
    public String username;
    public Role role;
}
