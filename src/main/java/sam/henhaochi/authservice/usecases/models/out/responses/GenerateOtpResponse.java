package sam.henhaochi.authservice.usecases.models.out.responses;

public interface GenerateOtpResponse {
    String getOtpNumber();
    boolean isOtpCreationStatusSuccess();
}
