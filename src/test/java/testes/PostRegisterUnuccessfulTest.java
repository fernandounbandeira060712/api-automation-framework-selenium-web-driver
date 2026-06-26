package testes;

import static enums.EndPointEnum.ENDPOINT_POST_REGISTER;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_REGISTER_UNSUCCESSFUL;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;
import static utils.Common.requestBodyEmail;

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
public class PostRegisterUnuccessfulTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Realizar registro sem sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida registro sem sucesso quando o password não é informado.")
    public void validarStatusCode400EResponseBody() {

        Map<String, Object> body = requestBodyEmail(
                USUARIO_VALIDO_POST_REGISTER_UNSUCCESSFUL.getEmail());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_REGISTER.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_BAD_REQUEST);

        validationSteps.validarCampoComMatcher(
                response,
                "error",
                equalTo("Missing password")
        );
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do registro sem sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de registro sem sucesso.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyEmail(
                USUARIO_VALIDO_POST_REGISTER_UNSUCCESSFUL.getEmail());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_REGISTER.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_BAD_REQUEST);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PostRegisterUnsuccessfulschema.json"
        );
    }
}