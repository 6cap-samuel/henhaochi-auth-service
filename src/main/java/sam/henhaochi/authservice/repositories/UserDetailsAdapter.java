package sam.henhaochi.authservice.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;
import sam.henhaochi.authservice.usecases.models.RegisterAccountUseCaseModel;
import sam.henhaochi.authservice.usecases.out.UserDetailsDataSource;

import javax.transaction.Transactional;

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
    public AccountCreationStatus register(
            final RegisterAccountUseCaseModel registerAccountUseCaseModel
    ) {
        UserDetailsEntity mappedRepositoryEntity =
                UserDetailsEntity.Mapper.fromRegisterUseCase(registerAccountUseCaseModel);

        log.info(
                String.format(
                        "UserDetailsRepository register called with username: %s, email: %s",
                        mappedRepositoryEntity.getUsername(),
                        mappedRepositoryEntity.getEmail()
                )
        );

        return this.userDetailsRepository.existsByUsernameAndEmail(
                mappedRepositoryEntity.getUsername(),
                mappedRepositoryEntity.getEmail()
        ) ? AccountCreationStatus.USERNAME_EXIST
                : this.createUser(mappedRepositoryEntity);
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