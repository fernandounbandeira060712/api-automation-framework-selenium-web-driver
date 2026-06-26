package testes;

import static enums.EndPointEnum.ENDPOINT_DELETE;

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
public class DeleteTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Excluir usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida a exclusão de um usuário e o retorno do status HTTP 204.")
    public void validarStatusCode204() {

        ValidatableResponse response =
                requestSteps.enviarDelete(
                        ENDPOINT_DELETE.getEndPoint());

        validationSteps.validarStatusCode(response, HttpStatus.SC_NO_CONTENT);
    }
}
