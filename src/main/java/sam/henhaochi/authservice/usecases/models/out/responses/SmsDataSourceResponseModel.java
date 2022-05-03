package sam.henhaochi.authservice.usecases.models.out.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmsDataSourceResponseModel implements
        SendSmsResponse {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Factory {
         public static SendSmsResponse newSendSmsResponse() {
             return SmsDataSourceResponseModel.builder()
                     .build();
         }
    }
}
