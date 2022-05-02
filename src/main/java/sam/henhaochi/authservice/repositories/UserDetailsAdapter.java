package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.constants.Role;
import sam.henhaochi.authservice.repositories.entities.AuthorityEntity;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.interfaces.out.UserDetailsDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.RegisterRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.RegisterResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.UserDetailsDataSourceResponseModel;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@AllArgsConstructor
@Slf4j
public class UserDetailsAdapter implements 
        UserDetailsService,
        UserDetailsDataSource {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.info(
                String.format(
                        "UserDetailsRepository load user by username called with %s",
                        username
                )
        );
        return this.userDetailsRepository.getById(username);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse register(
            final RegisterRequest registerRequest
    ) {
        UserDetailsEntity mappedRepositoryEntity =
                UserDetailsEntity.Mapper.from(
                        registerRequest,
                        Set.of(AuthorityEntity.Factory.newInstance(Role.ROLE_USER.name()))
                );

        log.info(
                String.format(
                        "UserDetailsRepository register called with username: %s, email: %s",
                        mappedRepositoryEntity.getUsername(),
                        mappedRepositoryEntity.getEmail()
                )
        );

        AccountCreationStatus accountCreationStatus =
                this.userDetailsRepository.existsByUsernameOrEmail(
                        mappedRepositoryEntity.getUsername(),
                        mappedRepositoryEntity.getEmail()
                ) ? AccountCreationStatus.USERNAME_EXIST
                        : this.createUser(mappedRepositoryEntity);

        return UserDetailsDataSourceResponseModel.Factory.newRegisterResponse(
                accountCreationStatus
        );
    }

    private AccountCreationStatus createUser(
            final UserDetailsEntity mappedRepositoryEntity
    ) {
        this.userDetailsRepository.save(
            mappedRepositoryEntity
        );
        return AccountCreationStatus.UNVERIFIED;
    }
}