package testes;

import static enums.EndPointEnum.ENDPOINT_GET_SINGLE_USER;

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
@Feature("Users")
public class GetSingleUsersTest extends BaseTest {


    @Test(groups = {"regressivo"})
    @Story("Buscar usuário por ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida status code e os principais campos do usuário retornado por ID.")
    public void validarStatusCode200EResponseBody() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_SINGLE_USER.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);

        validationSteps.validarCampoIgual(response, "data.id", 2);
        validationSteps.validarCampoIgual(response, "data.email", "janet.weaver@reqres.in");
        validationSteps.validarCampoIgual(response, "data.first_name", "Janet");
        validationSteps.validarCampoIgual(response, "data.last_name", "Weaver");
        validationSteps.validarCampoNaoNulo(response, "data.avatar");
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do usuário por ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de busca de usuário por ID.")
    public void validarSchema() {

        ValidatableResponse response =
                requestSteps.enviarGet(ENDPOINT_GET_SINGLE_USER.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_OK);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/GetSingleUsersSchema.json"
        );
    }
}