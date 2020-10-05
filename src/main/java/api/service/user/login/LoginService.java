package api.service.user.login;

import io.restassured.response.Response;

public interface LoginService {

    Response login(String userName, String password);

}
