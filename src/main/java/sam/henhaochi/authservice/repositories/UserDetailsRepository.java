package sam.henhaochi.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sam.henhaochi.authservice.repositories.entities.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, String> {
    boolean existsByUsernameOrEmail(String username, String email);
}
