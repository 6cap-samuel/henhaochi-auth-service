package sam.henhaochi.authservice.usecases.models.out.requests;

public interface VerifyOtpRequest {
    String getUsername();
    String getOtp();
}
