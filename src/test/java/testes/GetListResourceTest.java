package testes;

import bases.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_GET_LIST_RESOURCE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static services.Services.getSemAutenticacao;

public class GetListResourceTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {
        getSemAutenticacao(ENDPOINT_GET_LIST_RESOURCE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.size()", equalTo(6))
                .body("data[0].id", notNullValue())
                .body("data[0].name", notNullValue())
                .body("data[0].year", notNullValue())
                .body("data[0].color", notNullValue())
                .body("data[0].pantone_value", notNullValue());
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {
        getSemAutenticacao(ENDPOINT_GET_LIST_RESOURCE.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("arquivos/schemas/GetListResourceSchema.json"));
    }
}