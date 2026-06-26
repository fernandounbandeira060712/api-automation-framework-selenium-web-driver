package testes;

import static enums.EndPointEnum.ENDPOINT_POST_LOGIN;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_LOGIN_UNSUCCESSFUL_PASSWORD_NULL;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;
import static utils.Common.requestBodyEmail;

import java.util.Map;

import org.testng.annotations.Test;

import DataProviders.PostLoginUnsuccessfulDataProvider;
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
public class PostLoginUnsuccessfulTest extends BaseTest {

    @Test(
            groups = {"regressivo"},
            dataProviderClass = PostLoginUnsuccessfulDataProvider.class,
            dataProvider = "validarCamposObrigatorios"
    )
    @Story("Validar login sem sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida cenários obrigatórios do login, garantindo status 400 e mensagem de erro esperada.")
    public void validarStatusCode400ECamposObrigatorios(
            Map<String, Object> body,
            String mensagemEsperada) {

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_LOGIN.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_BAD_REQUEST);

        validationSteps.validarCampoComMatcher(
                response,
                "error",
                equalTo(mensagemEsperada)
        );
    }

    @Test(groups = {"contrato"})
    @Story("Validar contrato do login sem sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida o contrato JSON Schema do endpoint de login sem sucesso.")
    public void validarSchema() {

        Map<String, Object> body = requestBodyEmail(
                USUARIO_VALIDO_POST_LOGIN_UNSUCCESSFUL_PASSWORD_NULL.getEmail());

        ValidatableResponse response =
                requestSteps.enviarPost(
                        ENDPOINT_POST_LOGIN.getEndPoint(),
                        body);

        validationSteps.validarStatusCode(response, SC_BAD_REQUEST);

        validationSteps.validarContratoJsonSchema(
                response,
                "arquivos/schemas/PostLoginUnsuccessfulSchema.json"
        );
    }
}