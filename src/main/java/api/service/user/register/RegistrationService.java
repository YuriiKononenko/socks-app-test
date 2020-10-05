package api.service.user.register;

import api.model.register.RegisterModel;
import io.restassured.response.Response;

public interface RegistrationService {

    Response register(RegisterModel requestBody);

}
