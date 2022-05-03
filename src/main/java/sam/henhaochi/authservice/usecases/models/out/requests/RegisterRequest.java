package sam.henhaochi.authservice.usecases.models.out.requests;

public interface RegisterRequest {
    String getUsername();
    String getPassword();
    String getEmail();
}
