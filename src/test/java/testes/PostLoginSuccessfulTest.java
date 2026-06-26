package testes;

import static enums.EndPointEnum.ENDPOINT_POST_LOGIN;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_LOGIN_SUCCESSFUL;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.Common.requestBodyEmailPasssword;

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
@Feature("Authentication")
public class PostLoginSuccessfulTest extends BaseTest {


    @Test(groups = {"regressivo"})
    @Story("Realizar login com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida login com credenciais válidas e retorno do token de autenticação.")
    public void validarStatusCode200EResponseBody() {

        Map<String, Object> body = requestBodyEmailPasssword(
                USUARIO_VALIDO_POST_LOGIN_SUCCESSFUL.getEmail(),
                USUARIO_VALIDO_POST_LOGIN_SUCCESSFUL.getPassword());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_LOGIN.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);
        validationSteps.validarCampoNaoNulo(response, "token");
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de login.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyEmailPasssword(
                USUARIO_VALIDO_POST_LOGIN_SUCCESSFUL.getEmail(),
                USUARIO_VALIDO_POST_LOGIN_SUCCESSFUL.getPassword());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_LOGIN.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PostLoginSuccessfulSchema.json");
    }
}