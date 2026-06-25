package testes;

import bases.BaseTest;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_POST_REGISTER;
import static enums.UsuarioEnum.USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static services.Services.postComLoginNoBody;
import static utils.Common.requestBodyEmailPasssword;

public class PostRegisterSuccessfulTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {
        postComLoginNoBody(
                ENDPOINT_POST_REGISTER.getEndPoint(),
                requestBodyEmailPasssword(
                        USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getEmail(),
                        USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getPassword()))
                .statusCode(SC_OK)
                .body("id", equalTo(4))
                .body("token", notNullValue());
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {
        postComLoginNoBody(
                ENDPOINT_POST_REGISTER.getEndPoint(),
                requestBodyEmailPasssword(
                        USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getEmail(),
                        USUARIO_VALIDO_POST_REGISTER_SUCCESSFUL.getPassword()))
                .statusCode(SC_OK)
                .body(matchesJsonSchemaInClasspath(
                        "arquivos/schemas/PostRegisterSuccessfulSchema.json"));
    }
}