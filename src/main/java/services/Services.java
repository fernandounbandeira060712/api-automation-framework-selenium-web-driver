package services;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static utils.AllureUtils.attachJson;
import static utils.AllureUtils.attachText;
import static utils.AllureUtils.step;

public class Services {

    public static ValidatableResponse getSemAutenticacao(String endPoint) {

        step("Enviar requisição GET");

        attachText("Endpoint", endPoint);

        Response response = when()
                .get(endPoint);

        attachText("Status Code", String.valueOf(response.statusCode()));
        attachJson("Response Body", response.asPrettyString());

        return response.then();
    }

    public static ValidatableResponse deleteSemAutenticacao(String endPoint) {

        step("Enviar requisição DELETE");

        attachText("Endpoint", endPoint);

        Response response = when()
                .delete(endPoint);

        attachText("Status Code", String.valueOf(response.statusCode()));
        attachJson("Response Body", response.asPrettyString());

        return response.then();
    }

    public static ValidatableResponse postComLoginNoBody(String endPoint, Map<String, Object> body) {

        step("Enviar requisição POST com body");

        attachText("Endpoint", endPoint);
        attachJson("Request Body", body.toString());

        Response response = given()
                .body(body)
                .when()
                .post(endPoint);

        attachText("Status Code", String.valueOf(response.statusCode()));
        attachJson("Response Body", response.asPrettyString());

        return response.then();
    }

    public static ValidatableResponse putComLoginNoBody(String endPoint, Map<String, Object> body) {

        step("Enviar requisição PUT com body");

        attachText("Endpoint", endPoint);
        attachJson("Request Body", body.toString());

        Response response = given()
                .body(body)
                .when()
                .put(endPoint);

        attachText("Status Code", String.valueOf(response.statusCode()));
        attachJson("Response Body", response.asPrettyString());

        return response.then();
    }

    public static ValidatableResponse patchComLoginNoBody(String endPoint, Map<String, Object> body) {

        step("Enviar requisição PATCH com body");

        attachText("Endpoint", endPoint);
        attachJson("Request Body", body.toString());

        Response response = given()
                .body(body)
                .when()
                .patch(endPoint);

        attachText("Status Code", String.valueOf(response.statusCode()));
        attachJson("Response Body", response.asPrettyString());

        return response.then();
    }
}