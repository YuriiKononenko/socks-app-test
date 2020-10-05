package api.service.user.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.AppConfig.APP_BASE_URL;

public class LoginServiceImpl implements LoginService {

    @Override
    public Response login(String userName, String password) {
        return RestAssured.given()
                .baseUri(APP_BASE_URL)
                .contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic(userName, password)
                .get("/login");
    }
}
