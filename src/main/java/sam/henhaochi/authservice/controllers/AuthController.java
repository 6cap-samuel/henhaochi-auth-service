package sam.henhaochi.authservice.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sam.henhaochi.authservice.annotations.methods.WithCorsProtection;
import sam.henhaochi.authservice.controllers.mappers.LoginAccountRequestMapper;
import sam.henhaochi.authservice.controllers.mappers.LoginAccountResponseMapper;
import sam.henhaochi.authservice.controllers.requests.LoginRequest;
import sam.henhaochi.authservice.entities.Account;
import sam.henhaochi.authservice.exceptions.EmptyResult;
import sam.henhaochi.authservice.usecases.in.CheckTokenInput;
import sam.henhaochi.authservice.usecases.in.LoginAccountInput;
import sam.henhaochi.authservice.usecases.in.ValidateProfileInput;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final LoginAccountInput loginAccountInput;
    final LoginAccountRequestMapper loginAccountRequestMapper;
    final LoginAccountResponseMapper loginAccountResponseMapper;

    final CheckTokenInput checkTokenInput;
    final ValidateProfileInput validateProfileInput;

    private static final Logger logger
            = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    @WithCorsProtection
    public ResponseEntity<Object> loginWithUsernameAndPassword(
            @RequestBody LoginRequest loginRequest
    ) throws NoSuchAlgorithmException {
        logger.info("POST: /login called");
        Account foundAccount = loginAccountInput.with(
                loginAccountRequestMapper.mapToAccountEntity(
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
                    .body(loginAccountResponseMapper.mapToLoginResponse(
                            foundAccount)
                    );
        }
    }

    @GetMapping("/token")
    @WithCorsProtection
    public ResponseEntity<Object> checktokenValidity(
            @RequestHeader("token") String token
    ) {
        logger.info("POST: /token called");
        if (checkTokenInput.check(token)){
            return ResponseEntity.status(
                    HttpStatus.NO_CONTENT
            ).build();
        } else {
            return ResponseEntity.status(
                    HttpStatus.FORBIDDEN
            ).build();
        }
    }

    @GetMapping("/validate")
    @WithCorsProtection
    public ResponseEntity<Object> getProfile(
            @RequestHeader("token") String token
    ) {
        try {
             return ResponseEntity.ok(
                     loginAccountResponseMapper.mapToValidateResponse(
                             validateProfileInput.with(token)
                     )
             );
        } catch (EmptyResult emptyResult){
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND
            ).build();
        }
    }
}
