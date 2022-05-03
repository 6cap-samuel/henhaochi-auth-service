package sam.henhaochi.authservice.twilio;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sam.henhaochi.authservice.usecases.interfaces.out.SmsDataSource;
import sam.henhaochi.authservice.usecases.models.out.requests.SendSmsRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.SendSmsResponse;
import sam.henhaochi.authservice.usecases.models.out.responses.SmsDataSourceResponseModel;

@Component
public class TwilioAdapter implements SmsDataSource {

    @Value("${user.config.twilio.origin}")
    private String originNumber;

    @Override
    public SendSmsResponse sendSms(SendSmsRequest sendSmsRequest) {
       Message.creator(
                new PhoneNumber(sendSmsRequest.getPhoneNumber()),
                new PhoneNumber(originNumber),
                sendSmsRequest.getBodyText()
        ).create();

       return SmsDataSourceResponseModel.Factory
               .newSendSmsResponse();
    }
}
