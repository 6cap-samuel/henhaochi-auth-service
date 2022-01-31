package sam.henhaochi.authservice.usecases.in;

public interface CheckTokenInput {
    boolean check(
            String token
    );
}
