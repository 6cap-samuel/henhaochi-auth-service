package sam.henhaochi.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sam.henhaochi.authservice.usecases.interfaces.out.JwtDataSource;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtAdapter implements JwtDataSource {

    @Value("${user.config.private-key}")
    private String privateKey;

    @Value("${user.config.issuer}")
    private String issuer;

    @Value("${user.config.subject}")
    private String subject;

    @Value("${user.config.expiry-time}")
    private String expiryTime;

    @Override
    public String generateJwt(UserDetails userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .claim("username", userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Integer.parseInt(expiryTime)))
                .signWith(key)
                .compact();
    }
}
