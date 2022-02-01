package sam.henhaochi.authservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface TokenRepository
        extends CrudRepository<TokenEntity, String> {
    Optional<TokenEntity> findByTokenStringEqualsAndExpirationDateIsGreaterThan(
            String tokenString,
            Timestamp expirationDate
    );
}
