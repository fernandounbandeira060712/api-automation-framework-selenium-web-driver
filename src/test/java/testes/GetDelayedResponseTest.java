package testes;

import bases.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_GET_DELAYED_RESPONSE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static services.Services.getSemAutenticacao;

public class GetDelayedResponseTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {
        getSemAutenticacao(ENDPOINT_GET_DELAYED_RESPONSE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.size()", equalTo(6))
                .body("data[0].id", notNullValue())
                .body("data[0].email", notNullValue())
                .body("data[0].first_name", notNullValue())
                .body("data[0].last_name", notNullValue())
                .body("data[0].avatar", notNullValue());
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {
        getSemAutenticacao(ENDPOINT_GET_DELAYED_RESPONSE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("arquivos/schemas/GetDelayedResponseSchema.json"));
    }
}