package sam.henhaochi.authservice.configurations;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TwilioConfiguration {

    @Value("${user.config.twilio.account-sid}")
    private String accountSid;

    @Value("${user.config.twilio.auth-token}")
    private String authToken;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
}
