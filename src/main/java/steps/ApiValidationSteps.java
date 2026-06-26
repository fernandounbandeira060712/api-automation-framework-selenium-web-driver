package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ApiValidationSteps {

    @Step("Validar Status Code = {statusCode}")
    public void validarStatusCode(ValidatableResponse response, int statusCode) {
        response.statusCode(statusCode);
    }

    @Step("Validar campo '{campo}' igual a '{valorEsperado}'")
    public void validarCampoIgual(ValidatableResponse response, String campo, Object valorEsperado) {
        response.body(campo, equalTo(valorEsperado));
    }

    @Step("Validar campo '{campo}' não é nulo")
    public void validarCampoNaoNulo(ValidatableResponse response, String campo) {
        response.body(campo, notNullValue());
    }
    
    

    @Step("Validar campo '{campo}' com regex '{regex}'")
    public void validarCampoComRegex(ValidatableResponse response, String campo, String regex) {
        response.body(campo, matchesPattern(regex));
    }

    @Step("Validar campo '{campo}' com matcher customizado")
    public void validarCampoComMatcher(ValidatableResponse response, String campo, Matcher<?> matcher) {
        response.body(campo, matcher);
    }

    @Step("Validar contrato JSON Schema: {schemaPath}")
    public void validarContratoJsonSchema(ValidatableResponse response, String schemaPath) {
        response.body(matchesJsonSchemaInClasspath(schemaPath));
    }
    
    @Step("Validar body vazio")
    public void validarBodyVazio(ValidatableResponse response) {
        response.body("$", equalTo(new java.util.HashMap<>()));
    }
}