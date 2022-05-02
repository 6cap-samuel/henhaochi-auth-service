package sam.henhaochi.authservice.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sam.henhaochi.authservice.controllers.requests.LoginRequest;
import sam.henhaochi.authservice.usecases.interfaces.in.LoginAccountInput;
import sam.henhaochi.authservice.usecases.models.in.responses.LoginAccountUseCaseResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final LoginAccountInput loginAccountInput;

    private static final Logger logger
            = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<Object> loginWithUsernameAndPassword(
            @RequestBody LoginRequest loginRequest
    ) {
        logger.info("POST: /login called");
        LoginAccountUseCaseResponse foundAccount = loginAccountInput.with(
                LoginRequest.Mapper.mapToLoginRequestModel(
                        loginRequest
                )
        );

        if (foundAccount == null) {
            return ResponseEntity.status(
                    HttpStatus.FORBIDDEN
            ).contentType(
                    MediaType.APPLICATION_JSON
            ).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(foundAccount);
        }
    }
}
