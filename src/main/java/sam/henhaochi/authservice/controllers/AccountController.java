package sam.henhaochi.authservice.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sam.henhaochi.authservice.annotations.methods.WithCorsProtection;
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.controllers.mappers.RegisterAccountRequestMapper;
import sam.henhaochi.authservice.controllers.requests.RegisterAccountRequest;
import sam.henhaochi.authservice.usecases.in.RegisterAccountInput;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    final RegisterAccountInput registerAccountInput;
    final RegisterAccountRequestMapper requestMapper;

    private static final Logger logger
            = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @WithCorsProtection
    public ResponseEntity<Object> register(
            @RequestBody final RegisterAccountRequest request
    ) throws NoSuchAlgorithmException, URISyntaxException {
        logger.info("POST: /register called");
        AccountCreationStatus status = registerAccountInput.with(
                requestMapper.mapToAccountEntity(
                        request
                )
        );
        if (status.equals(AccountCreationStatus.SUCCESS)){
            return ResponseEntity.created(
                    new URI("https://henhaochi.io")
            ).build();
        }
        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).build();
    }
}
