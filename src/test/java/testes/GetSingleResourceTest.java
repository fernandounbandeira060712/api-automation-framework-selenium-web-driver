package testes;

import bases.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_GET_SINGLE_RESOURCE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static services.Services.getSemAutenticacao;

public class GetSingleResourceTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {
        getSemAutenticacao(ENDPOINT_GET_SINGLE_RESOURCE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {
        getSemAutenticacao(ENDPOINT_GET_SINGLE_RESOURCE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("arquivos/schemas/GetSingleResourceSchema.json"));
    }
}