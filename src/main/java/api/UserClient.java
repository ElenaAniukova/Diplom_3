package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;
import static api.Config.*;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя через API")
    public Response createUser(String email, String password, String name) {
        Map<String, String> userMap = Map.of(
                "email", email,
                "password", password,
                "name", name
        );
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(userMap)
                .when()
                .post(USER_REGISTER);
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String tokenForDeletion) {
        given()
                .header("Authorization", tokenForDeletion)
                .baseUri(BASE_URL)
                .when()
                .delete(USER_AUTH)
                .then()
                .statusCode(202);
    }
}
