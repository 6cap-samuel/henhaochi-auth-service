package sam.henhaochi.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sam.henhaochi.authservice.repositories.entities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    boolean existsByCodeAndUserDetails_Username(
            String code,
            String username
    );

    void deleteAllByUserDetails_Username(
            String username
    );
}
