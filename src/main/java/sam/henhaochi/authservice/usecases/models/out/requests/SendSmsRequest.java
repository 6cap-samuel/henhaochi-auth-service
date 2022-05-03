package sam.henhaochi.authservice.usecases.models.out.requests;

public interface SendSmsRequest {
    String getPhoneNumber();
    String getBodyText();
}
