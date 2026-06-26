package testes;

import static enums.EndPointEnum.ENDPOINT_GET_SINGLE_RESOURCE;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import bases.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;

@Epic("ReqRes API")
@Feature("Resources")
public class GetSingleResourceTest extends BaseTest {


    @Test(groups = {"regressivo"})
    @Story("Buscar resource por ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida status code e campos principais do resource retornado por ID.")
    public void validarStatusCode200EResponseBody() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_SINGLE_RESOURCE.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);

        validationSteps.validarCampoIgual(response, "data.id", 2);
        validationSteps.validarCampoIgual(response, "data.name", "fuchsia rose");
        validationSteps.validarCampoIgual(response, "data.year", 2001);
        validationSteps.validarCampoIgual(response, "data.color", "#C74375");
        validationSteps.validarCampoIgual(response, "data.pantone_value", "17-2031");
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do resource por ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de busca de resource por ID.")
    public void validarSchema() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_SINGLE_RESOURCE.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/GetSingleResourceSchema.json"
        );
    }
}