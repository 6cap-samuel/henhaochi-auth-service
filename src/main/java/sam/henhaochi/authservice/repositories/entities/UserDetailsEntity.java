package sam.henhaochi.authservice.repositories.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sam.henhaochi.authservice.usecases.models.in.RegisterAccountUseCaseRequestModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@Entity(name="UserDetails")
public class UserDetailsEntity implements UserDetails {

    @Id
    private String username;
    private String password;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @OneToMany(
            targetEntity = AuthorityEntity.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "username")
    private Set<AuthorityEntity> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static UserDetailsEntity newNormalUserInstance(
                final String username,
                final String password,
                final String email,
                final Set<AuthorityEntity> authorityEntities
        ) {
            return UserDetailsEntity.of(
                    username,
                    password,
                    email,
                    false,
                    false,
                    false,
                    false,
                    authorityEntities
            );
        }
    }

    public static class Mapper {
         public static UserDetailsEntity fromRegisterUseCase(
                 final RegisterAccountUseCaseRequestModel registerAccountUseCaseRequestModel,
                 final Set<AuthorityEntity> authorityEntities
         ) {
             return UserDetailsEntity.Factory.newNormalUserInstance(
                     registerAccountUseCaseRequestModel.getUsername(),
                     registerAccountUseCaseRequestModel.getPassword(),
                     registerAccountUseCaseRequestModel.getEmail(),
                     authorityEntities
             );
         }
    }
}
