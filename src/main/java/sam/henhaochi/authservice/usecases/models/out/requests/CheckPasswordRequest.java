package sam.henhaochi.authservice.usecases.models.out.requests;

public interface CheckPasswordRequest {
    String getInputPassword();
    String getHashedPassword();
}
