package sam.henhaochi.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sam.henhaochi.authservice.repositories.entities.OtpEntity;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByCodeAndUserDetails_Username(
            String code,
            String username
    );

    boolean existsByCodeAndUserDetails_Username(
            String code,
            String username
    );

    void deleteAllByUserDetails_Username(
            String username
    );
}
