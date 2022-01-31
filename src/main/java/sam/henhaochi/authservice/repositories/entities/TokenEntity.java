package sam.henhaochi.authservice.repositories.entities;

import lombok.*;
import sam.henhaochi.authservice.utilities.HashGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="Token")
public class TokenEntity {

    @Id
    private String tokenString;

    @ManyToOne
    @JoinColumn(name = "id")
    private AccountEntity account;

    private Timestamp expirationDate;

    public static TokenEntity newInstance(
            AccountEntity account
    ) throws NoSuchAlgorithmException {
        Timestamp curTime = new Timestamp(
                System.currentTimeMillis() + 1000 * 60 * 60
        );
        return TokenEntity.builder()
                .tokenString(
                        HashGenerator.generateHash(
                                account.getUsername() + curTime
                        )
                ).account(account)
                .expirationDate(curTime)
                .build();
    }
}
