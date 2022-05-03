package sam.henhaochi.authservice.usecases.interfaces.out;

import sam.henhaochi.authservice.usecases.models.out.requests.SendSmsRequest;
import sam.henhaochi.authservice.usecases.models.out.responses.SendSmsResponse;

public interface SmsDataSource {
     SendSmsResponse sendSms(
             final SendSmsRequest sendSmsRequest
     );
}
