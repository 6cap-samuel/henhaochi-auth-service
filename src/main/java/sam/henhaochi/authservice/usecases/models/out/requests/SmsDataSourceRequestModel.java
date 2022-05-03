package sam.henhaochi.authservice.usecases.models.out.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SmsDataSourceRequestModel
        implements SendSmsRequest {

    private final String phoneNumber;
    private final String bodyText;

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String getBodyText() {
        return this.bodyText;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static SendSmsRequest newSendSmsRequest(
                 final String phoneNumber,
                 final String bodyText
         ) {
             return SmsDataSourceRequestModel.builder()
                     .phoneNumber(phoneNumber)
                     .bodyText(bodyText)
                     .build();
         }
    }
}
