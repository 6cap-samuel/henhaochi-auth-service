package sam.henhaochi.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sam.henhaochi.authservice.repositories.entities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> { }
