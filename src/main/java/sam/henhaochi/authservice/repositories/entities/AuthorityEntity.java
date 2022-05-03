package sam.henhaochi.authservice.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@Entity(name="Authority")
public class AuthorityEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static AuthorityEntity newInstance(String authority){
            return AuthorityEntity.of(
                    null,
                    authority
            );
        }
    }
}
