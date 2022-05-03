package sam.henhaochi.authservice.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.*;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@Entity(name="OTP")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String code;

    @OneToOne
    private UserDetailsEntity userDetails;

    private OffsetDateTime expiryDate;

    public boolean isExpired() {
        return expiryDate.toEpochSecond() - ZonedDateTime.now().toEpochSecond() < 0;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
        public static OtpEntity newInstance(
                final String code,
                final UserDetailsEntity userDetails
        ) {
            return OtpEntity.of(
                    null,
                    code,
                    userDetails,
                    Instant.now().atOffset(ZoneOffset.UTC).plusMinutes(1)
            );
        }
    }
}
