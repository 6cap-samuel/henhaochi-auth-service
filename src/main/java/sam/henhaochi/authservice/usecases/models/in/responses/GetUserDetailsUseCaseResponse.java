package sam.henhaochi.authservice.usecases.models.in.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import sam.henhaochi.authservice.repositories.entities.AuthorityEntity;

import java.util.Set;

@Data
@Builder
public class GetUserDetailsUseCaseResponse {

    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @JsonIgnore
    private boolean isAccountExist;
    private Set<AuthorityEntity> authorities;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static GetUserDetailsUseCaseResponse empty(){
             return GetUserDetailsUseCaseResponse.builder()
                     .isAccountExist(false)
                     .build();
        }

        public static GetUserDetailsUseCaseResponse newInstance(
                final UserDetails userDetails
        ) {
            Set.copyOf(userDetails.getAuthorities());
            return GetUserDetailsUseCaseResponse.builder()
                    .username(userDetails.getUsername())
                    .isAccountNonExpired(userDetails.isAccountNonExpired())
                    .isAccountNonLocked(userDetails.isAccountNonLocked())
                    .isCredentialsNonExpired(userDetails.isCredentialsNonExpired())
                    .isEnabled(userDetails.isEnabled())
                    .authorities((Set<AuthorityEntity>) userDetails.getAuthorities())
                    .isAccountExist(true)
                    .build();

        }
    }
}
