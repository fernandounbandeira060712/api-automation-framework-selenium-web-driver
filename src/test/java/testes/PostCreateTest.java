package testes;

import static enums.EndPointEnum.ENDPOINT_POST_CREATE;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_CREATE;
import static org.apache.http.HttpStatus.SC_CREATED;
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
public class PostCreateTest extends BaseTest {

    @Test(groups = {"regressivo"})
    @Story("Criar usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida criação de usuário via POST com status 201 e campos retornados.")
    public void validarStatusCode201EResponseBody() {

        Map<String, Object> body = requestBodyNameJob(
                USUARIO_VALIDO_POST_CREATE.getName(),
                USUARIO_VALIDO_POST_CREATE.getJob());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_CREATE.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_CREATED);
        validationSteps.validarCampoIgual(response, "name", USUARIO_VALIDO_POST_CREATE.getName());
        validationSteps.validarCampoIgual(response, "job", USUARIO_VALIDO_POST_CREATE.getJob());
        validationSteps.validarCampoComRegex(response, "id", "\\d+");
        validationSteps.validarCampoComRegex(
                response,
                "createdAt",
                "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"
        );
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato da criação de usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint POST de criação de usuário.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyNameJob(
                USUARIO_VALIDO_POST_CREATE.getName(),
                USUARIO_VALIDO_POST_CREATE.getJob());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_CREATE.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_CREATED);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PostCreateSchema.json"
        );
    }
}