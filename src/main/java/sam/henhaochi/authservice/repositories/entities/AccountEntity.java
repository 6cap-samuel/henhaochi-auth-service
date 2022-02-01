package sam.henhaochi.authservice.repositories.entities;

import lombok.*;
import sam.henhaochi.authservice.constants.Role;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="Account")
public class AccountEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
