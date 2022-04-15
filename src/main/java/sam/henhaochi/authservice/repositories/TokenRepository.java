package sam.henhaochi.authservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sam.henhaochi.authservice.repositories.entities.TokenEntity;

import java.sql.Timestamp;

@Repository
public interface TokenRepository
        extends CrudRepository<TokenEntity, String> {
    boolean existsByTokenStringEqualsAndExpirationDateIsGreaterThan(
            String tokenString,
            Timestamp expirationDate
    );
}
