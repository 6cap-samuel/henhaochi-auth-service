package sam.henhaochi.authservice.usecases.models.out.responses;

import sam.henhaochi.authservice.constants.OtpCreationStatus;

public interface GenerateOtpResponse {
    OtpCreationStatus getOtpCreationStatus();
}
