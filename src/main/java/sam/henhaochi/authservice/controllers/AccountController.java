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
import sam.henhaochi.authservice.constants.AccountCreationStatus;
import sam.henhaochi.authservice.controllers.requests.RegisterAccountRequest;
import sam.henhaochi.authservice.usecases.interfaces.in.RegisterAccountInput;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    final RegisterAccountInput registerAccountInput;

    private static final Logger logger
            = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> register(
            @RequestBody RegisterAccountRequest request
    ) {
        logger.info("POST: /register called");
        AccountCreationStatus status = registerAccountInput.create(
                RegisterAccountRequest.Mapper
                        .mapToRegisterUseCase(
                                request
                        )
        );

        if (status.equals(AccountCreationStatus.UNVERIFIED)) {
            return ResponseEntity.status(
                    HttpStatus.CREATED
            ).body(
                    AccountCreationStatus.UNVERIFIED
            );
        }

        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).build();
    }
}
