package api.service.user.register;


import api.model.register.RegisterModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.AppConfig.APP_BASE_URL;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public Response register(RegisterModel userInformation) {
        return RestAssured.given()
                .baseUri(APP_BASE_URL)
                .contentType(ContentType.JSON)
                .body(userInformation)
                .post("/register");
    }

}
