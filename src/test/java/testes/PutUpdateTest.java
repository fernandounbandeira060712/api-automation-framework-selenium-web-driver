package testes;

import static enums.EndPointEnum.ENDPOINT_PUT_UPDATE;
import static enums.UsuarioEnum.USUARIO_VALIDO_UPDATE;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.Common.requestBodyNameJob;

import java.util.Map;

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
public class PutUpdateTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Atualizar usuário")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida atualização completa de usuário via PUT.")
    public void validarStatusCode200EResponseBody() {

        Map<String, Object> body = requestBodyNameJob(
                USUARIO_VALIDO_UPDATE.getName(),
                USUARIO_VALIDO_UPDATE.getJob());

        ValidatableResponse response =
                requestSteps.enviarPut(
                        ENDPOINT_PUT_UPDATE.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);
        validationSteps.validarCampoIgual(response, "name", USUARIO_VALIDO_UPDATE.getName());
        validationSteps.validarCampoIgual(response, "job", USUARIO_VALIDO_UPDATE.getJob());
        validationSteps.validarCampoComRegex(
                response,
                "updatedAt",
                "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"
        );
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato da atualização de usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint PUT de atualização de usuário.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyNameJob(
                USUARIO_VALIDO_UPDATE.getName(),
                USUARIO_VALIDO_UPDATE.getJob());

        ValidatableResponse response =
                requestSteps.enviarPut(
                        ENDPOINT_PUT_UPDATE.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PutUpdateSchema.json"
        );
    }
}