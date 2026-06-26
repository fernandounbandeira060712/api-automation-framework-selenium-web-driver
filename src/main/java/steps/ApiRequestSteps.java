package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static services.Services.*;

public class ApiRequestSteps {

    @Step("Enviar requisição GET para o endpoint: {endpoint}")
    public ValidatableResponse enviarGet(String endpoint) {
        return getSemAutenticacao(endpoint);
    }

    @Step("Enviar requisição DELETE para o endpoint: {endpoint}")
    public ValidatableResponse enviarDelete(String endpoint) {
        return deleteSemAutenticacao(endpoint);
    }

    @Step("Enviar requisição POST para o endpoint: {endpoint}")
    public ValidatableResponse enviarPost(String endpoint, Map<String, Object> body) {
        return postComLoginNoBody(endpoint, body);
    }

    @Step("Enviar requisição PUT para o endpoint: {endpoint}")
    public ValidatableResponse enviarPut(String endpoint, Map<String, Object> body) {
        return putComLoginNoBody(endpoint, body);
    }

    @Step("Enviar requisição PATCH para o endpoint: {endpoint}")
    public ValidatableResponse enviarPatch(String endpoint, Map<String, Object> body) {
        return patchComLoginNoBody(endpoint, body);
    }
}