package testes;

import static enums.EndPointEnum.ENDPOINT_POST_REGISTER;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL;
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
public class PostRegisterSuccessfulTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Realizar registro com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida registro com dados válidos e retorno de id e token.")
    public void validarStatusCode200EResponseBody() {

        Map<String, Object> body = requestBodyEmailPasssword(
                USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getEmail(),
                USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getPassword());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_REGISTER.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);
        validationSteps.validarCampoIgual(response, "id", 4);
        validationSteps.validarCampoNaoNulo(response, "token");
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do registro com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de registro com sucesso.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyEmailPasssword(
                USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getEmail(),
                USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getPassword());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_REGISTER.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_OK);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PostRegisterSuccessfulSchema.json"
        );
    }
}