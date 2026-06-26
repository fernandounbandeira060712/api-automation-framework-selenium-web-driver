package testes;

import static enums.EndPointEnum.ENDPOINT_GET_SINGLE_RESOURCE_NOT_FOUND;

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
public class GetSingleResourceNotFoundTest extends BaseTest {


    @Test(groups = {"regressivo"})
    @Story("Buscar resource inexistente")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida que a API retorna 404 e body vazio quando o resource não existe.")
    public void validarStatusCode404EResponseBody() {

        ValidatableResponse response =
                requestSteps.enviarGet(
                        ENDPOINT_GET_SINGLE_RESOURCE_NOT_FOUND.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_NOT_FOUND);
        validationSteps.validarBodyVazio(response);
    }
}