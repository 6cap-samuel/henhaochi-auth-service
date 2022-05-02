package sam.henhaochi.authservice.entities;

import lombok.*;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

@Data
@AllArgsConstructor(staticName = "of")
public class UserDetails {

    private String username;
    private String password;
    private String email;

}
