package testes;

import static enums.EndPointEnum.ENDPOINT_GET_DELAYED_RESPONSE;

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
@Feature("Delayed Response")
public class GetDelayedResponseTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Validar resposta da lista de usuários com delay")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida status code e campos principais do endpoint delayed response.")
    public void validarStatusCode200EResponseBody() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_DELAYED_RESPONSE.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);

        validationSteps.validarCampoIgual(response, "page", 2);
        validationSteps.validarCampoIgual(response, "per_page", 6);
        validationSteps.validarCampoIgual(response, "total", 12);
        validationSteps.validarCampoIgual(response, "total_pages", 2);
        validationSteps.validarCampoIgual(response, "data.size()", 6);

        validationSteps.validarCampoNaoNulo(response, "data[0].id");
        validationSteps.validarCampoNaoNulo(response, "data[0].email");
        validationSteps.validarCampoNaoNulo(response, "data[0].first_name");
        validationSteps.validarCampoNaoNulo(response, "data[0].last_name");
        validationSteps.validarCampoNaoNulo(response, "data[0].avatar");
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do delayed response")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint delayed response.")
    public void validarSchema() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_DELAYED_RESPONSE.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);
        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/GetDelayedResponseSchema.json"
        );
    }
}