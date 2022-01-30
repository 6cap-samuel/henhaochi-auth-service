package sam.henhaochi.authservice.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HashGeneratorTest {

    private HashGenerator hashGenerator;

    @BeforeEach
    void before() {
        hashGenerator = new HashGenerator();
    }

    @Test
    public void shouldReturnEncryptedString()
            throws NoSuchAlgorithmException {
        assertNotEquals(
                "testing",
                hashGenerator.generateHash("testing")
        );
    }
}