package sam.henhaochi.authservice.repositories.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="Authority")
public class AuthorityEntity implements GrantedAuthority {

    @Id
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
